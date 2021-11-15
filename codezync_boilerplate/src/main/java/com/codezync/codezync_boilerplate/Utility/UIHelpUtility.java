package com.codezync.codezync_boilerplate.Utility;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

public class UIHelpUtility {

    //using this method should be able to hide the soft keyboard
    public static void hideSoftKeyboard(Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    View view = activity.getCurrentFocus();
                    if (view == null) {
                        view = new View(activity);
                    }
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                } catch (Exception e) {

                }
            }
        },1000);
    }

    public static void showBottomSheetDialog(Activity activity, int layout) {
//        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_add_new_card, null);
        View view = activity.getLayoutInflater().inflate(layout, null);

        BottomSheetDialog dialog = new BottomSheetDialog(activity);

        dialog.setContentView(view);
        dialog.show();
    }

    public static void popupBottomSheetDialogFragment(Activity activity,BottomSheetDialogFragment dialogFragment) {
        dialogFragment = new BottomSheetDialogFragment();
        dialogFragment.show(((FragmentActivity) activity).getSupportFragmentManager(), dialogFragment.getTag());
    }

    public static BottomSheetDialog getBottomSheet(Activity activity, int layout) {
//        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_add_new_card, null);
        View view = activity.getLayoutInflater().inflate(layout, null);

        BottomSheetDialog dialog = new BottomSheetDialog(activity);

        dialog.setContentView(view);
        return dialog;
    }

    //this method will help to make activity full screen
    public static void makeActivityFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //hide navigation bar
    public static void hideNavigationBar(AppCompatActivity activity) {
        activity.getSupportActionBar().hide();
    }

    //Show soft keyboard and focus a view
    public static void showSoftKeyboard(Activity activity, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
            }
        }, 100);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    //using this method user can remove color in status bar and make it transparent
    public static void makeStatusBarTransparent(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }


    public static BitmapImageViewTarget createBitmapImage(ImageView imageView, Context context) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
    }

    public static void loadImageFromServer(ImageView imageView, Context context, String url, int placeHolderImage) {

        Glide.with(context).load(url)
                .asBitmap().centerCrop()
                .error(placeHolderImage)
                .placeholder(placeHolderImage)
                .into(imageView);

    }

    public static void loadImageFromServerWithoutCenterCrop(ImageView imageView, Context context, String url, int placeHolderImage) {

        Glide.with(context).load(url)
                .error(placeHolderImage)
                .placeholder(placeHolderImage)
                .into(imageView);

    }

    public static void loadCircularImageFromServer(ImageView imageView, Context context, String url, int placeHolderImage) {

        Glide.with(context).load(url)
                .asBitmap().centerCrop()
                .error(placeHolderImage)
                .placeholder(placeHolderImage)
                .into(createBitmapImage(imageView, context));

    }


    public static void loadImageFromServer(ImageView imageView, Context context, String url, boolean isCircleImageView, int placeHolderImage, int width, int height) {
        if (isCircleImageView) {
            Glide.with(context).load(url)
                    .asBitmap().centerCrop()
                    .override(width, height)
                    .error(placeHolderImage)
                    .placeholder(placeHolderImage)
                    .into(createBitmapImage(imageView, context));
        } else {
            Glide.with(context).load(url)
                    .asBitmap().centerCrop()
                    .error(placeHolderImage)
                    .placeholder(placeHolderImage)
                    .into(imageView);
        }
    }

    public static void loadImageFromServer(ImageView imageView, Context context, String url, boolean isCircleImageView, int placeHolderImage) {
        if (isCircleImageView) {
            Glide.with(context).load(imageView)
                    .asBitmap().centerCrop()
                    .override(200, 200)
                    .error(placeHolderImage)
                    .placeholder(placeHolderImage)
                    .into(createBitmapImage(imageView, context));
        } else {
            Glide.with(context).load(imageView)
                    .asBitmap().centerCrop()
                    .error(placeHolderImage)
                    .override(200, 200)
                    .placeholder(placeHolderImage)
                    .into(imageView);
        }
    }

    //using this method should be able to change the layout of selected tab and unselected tab in tab bar
    public static void setTabBackground(int selectedUI, int unselectedUI, TabLayout tabLayout) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ViewGroup tabStrip = (ViewGroup) tabLayout.getChildAt(0);

            int selected = tabLayout.getSelectedTabPosition();

            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                View tabView = tabStrip.getChildAt(i);
                if (tabView != null) {
                    if (selected == i) {
                        int paddingStart = tabView.getPaddingStart();
                        int paddingTop = tabView.getPaddingTop();
                        int paddingEnd = tabView.getPaddingEnd();
                        int paddingBottom = tabView.getPaddingBottom();
                        ViewCompat.setBackground(tabView, AppCompatResources.getDrawable(tabView.getContext(), selectedUI));
                        ViewCompat.setPaddingRelative(tabView, paddingStart, paddingTop, paddingEnd, paddingBottom);
                    } else {
                        int paddingStart = tabView.getPaddingStart();
                        int paddingTop = tabView.getPaddingTop();
                        int paddingEnd = tabView.getPaddingEnd();
                        int paddingBottom = tabView.getPaddingBottom();
                        ViewCompat.setBackground(tabView, AppCompatResources.getDrawable(tabView.getContext(), unselectedUI));
                        ViewCompat.setPaddingRelative(tabView, paddingStart, paddingTop, paddingEnd, paddingBottom);
                    }
                }
            }

        }
    }


    //this method will hide bottom navigation bar. Note : should call in "onWindowFocusChanged" event of life cycle
    public static void hideSoftNavigationBar(Activity activity, boolean hasFocus) {
        if (hasFocus) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @WorkerThread
    public static Bitmap createRoundedBitmapFromUrl(String avatarUrl, Context context) throws Exception {
        return Glide.with(context).
                load(avatarUrl).
                asBitmap().
                into(80, 80).get();
    }


}
