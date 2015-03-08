package app.truefleet.com.truefleet.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Events.LinehaulSelectionEvent;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.LinehaulAdapter;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.LinehaulType;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.TrueFleetApp;
import app.truefleet.com.truefleet.Utils;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class OrderDetailsFragment extends Fragment implements Updater {

    private static final String IMAGE_DIRECTORY_NAME = "trufleet";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int REQUEST_CODE = 100;
    private Uri fileUri;
    private ActiveOrderManager activeOrderManager;
    private LinehaulType linehaulSelection;
    private static final String LOG_TAG = OrderDetailsFragment.class.getSimpleName();

    @InjectView(R.id.order_id)
    TextView orderid;
    @InjectView(R.id.order_receipt_date)
    TextView receiptDate;
    @InjectView(R.id.order_notes)
    TextView orderNotes;
    @InjectView(R.id.btnAddImage)
    com.gc.materialdesign.views.ButtonRectangle addImageButton;

    @InjectView(R.id.spinner_order_status)
    Spinner spinnerStatus;

    @InjectView(R.id.button_backtoactive)
    com.gc.materialdesign.views.ButtonRectangle backToActiveButton;

    @InjectView(R.id.button_update_status)
    com.gc.materialdesign.views.ButtonRectangle statusButton;
    @InjectView(R.id.listview_linehauls)
    ListView listView;

    @Inject
    Bus bus;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linehaulSelection = LinehaulType.ACTIVE;
        activeOrderManager = ActiveOrderManager.getInstance();
        activeOrderManager.setLinehaulUpdater(this);
        TrueFleetApp.inject(this);
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.inject(this, view);

        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePhoto(v);
            }
        });


        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        bus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }


    public void updateUI() {

        Order o = activeOrderManager.getOrder();
        orderid.setText(String.valueOf(o.orderid));
        receiptDate.setText(o.convertDateTime(o.receiptdate).toString());
        orderNotes.setText(o.notes);

        List<Linehaul> arrayOfLinehauls = new ArrayList<>();

        arrayOfLinehauls = activeOrderManager.getSelectedLinehauls();

        if (Utils.isNullOrEmpty(arrayOfLinehauls));

        LinehaulAdapter adapter = new LinehaulAdapter(getActivity(), arrayOfLinehauls);

            listView.setAdapter(adapter);
            listView.setItemsCanFocus(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                try {
                activeOrderManager.setActiveLinehaul((Linehaul)parent.getAdapter().getItem(position));
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Invalid linehaul object in linehaull list");
                }
            }
        });

        int selectedPosition = 0;
        listView.setItemChecked(0, true);
        listView.setSelection(0);
    }
    private void setNonoactiveDisplay() {
        addImageButton.setVisibility(View.INVISIBLE);
        orderNotes.setVisibility(View.INVISIBLE);
        statusButton.setVisibility(View.INVISIBLE);
        spinnerStatus.setVisibility(View.INVISIBLE);
        backToActiveButton.setVisibility(View.VISIBLE);
    }

    private void setActiveDisplay() {
        addImageButton.setVisibility(View.VISIBLE);
        orderNotes.setVisibility(View.VISIBLE);
        statusButton.setVisibility(View.VISIBLE);
        spinnerStatus.setVisibility(View.VISIBLE);
        backToActiveButton.setVisibility(View.GONE);
    }


    //Result from camera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            try {
                if (resultCode == Activity.RESULT_OK) {

                    showImageDialog();
                } else {
                    Log.i(LOG_TAG, "Not result OK from camera");
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, e.toString());
            }
        }
    }

    public void showImageDialog() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                options);

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(bitmap);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity()).
                        setTitle("Upload to server?").
                        setPositiveButton("Send to Server", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //TODO: Send to server when implemented
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setView(imageView);

        builder.create().show();


    }
    private void takePhoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(LOG_TAG, "Failed to create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    private boolean checkCameraSupport() {
        if (getActivity().getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {

            return true;
        } else {
            return false;
        }
    }
    @Subscribe
    public void linehaulTypeSeelction(LinehaulSelectionEvent event) {
        linehaulSelection = event.getSelectionType();

        if (linehaulSelection != LinehaulType.ACTIVE)
            setNonoactiveDisplay();
        else
            setActiveDisplay();
        updateUI();
    }
}