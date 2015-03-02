package app.truefleet.com.truefleet.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Adapters.LinehaulAdapter;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class OrderDetailsFragment extends Fragment implements  Updater  {
    private static final String IMAGE_DIRECTORY_NAME = "trufleet";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int REQUEST_CODE = 100;
    private Uri fileUri;
    private ActiveOrderManager activeOrderManager;

    private static final String LOG_TAG = OrderDetailsFragment.class.getSimpleName();
    ActiveLinehaulFragment activeLinehaulFragment;
    TextView orderid;
    TextView receiptDate;
    TextView orderNotes;
    Button addImageButton;
    ImageView imageView;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //TODO: Change to home in real app with selected order

        activeOrderManager = ActiveOrderManager.getInstance();

        activeOrderManager.setLinehaulUpdater(this);
        //activeOrderManager.setOrder(Order.load(Order.class, 1));


        View view = inflater.inflate(R.layout.fragment_order, container, false);
        listView = (ListView) view.findViewById(R.id.listview_linehauls);
        activeLinehaulFragment = new ActiveLinehaulFragment();
        orderid = (TextView)view.findViewById(R.id.order_id);
        receiptDate = (TextView)view.findViewById(R.id.order_receipt_date);
        orderNotes = (TextView)view.findViewById(R.id.order_notes);

        addImageButton = (Button)view.findViewById(R.id.btnAddImage);
        imageView = (ImageView)view.findViewById(R.id.orderImageView);

        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePhoto(v);

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showImage();
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    public void updateUI() {
        Order o = activeOrderManager.getOrder();
        orderid.setText(String.valueOf(o.orderid));
        receiptDate.setText(o.receiptDate.toString());
        orderNotes.setText(o.notes);

        List<Linehaul> arrayOfLinehauls;
        arrayOfLinehauls = activeOrderManager.getLinehauls();

        LinehaulAdapter adapter= new LinehaulAdapter(getActivity(), arrayOfLinehauls);

        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                activeOrderManager.setActiveLinehaulIndex(position);


            }
        });

        int selectedPosition = 0;
        //listView.requestFocusFromTouch();

       // listView.getAdapter().getView(0,null,null).setSelected(true);
       // listView.performItemClick(
         //       listView.getAdapter().getView(selectedPosition, null, null), selectedPosition, selectedPosition);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==REQUEST_CODE) {
            try {
                if (resultCode == Activity.RESULT_OK) {

                    // bimatp factory
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    // downsizing image as it throws OutOfMemory Exception for larger
                    // images
                    //options.inSampleSize = 8;

                    final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                            options);
                    System.out.println("width: " + bitmap.getWidth());
                    imageView.setImageBitmap(bitmap);
                }
                else {
                    Log.i(LOG_TAG, "Not result OK from camera");
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, e.toString());
            }
        }
    }

    public void showImage() {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        System.out.println("Width: " + bitmap.getWidth());
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.BLACK));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(bitmap);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
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
        }  else {
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

}