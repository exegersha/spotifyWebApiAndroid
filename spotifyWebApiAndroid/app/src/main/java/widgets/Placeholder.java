package widgets;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

public class Placeholder {

    /**
     * This is a helper function created to correct the radial gradient on backgrounds.
     * The gradient radius is supposed to be half the width of the view, but its not possible
     * to make a dynamic gradient radius in xml.
     * This function deliberately doesn't catch any class cast exceptions etc
     * If something goes wrong here its a programmer error and needs to be fixed before release
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("deprecation")
    public static final void set(ImageView view, int resourceId) {

        final int width = view.getMeasuredWidth();
        final int height = view.getMeasuredHeight();

        final Drawable background = inflatePlaceholderLayerDrawable(view.getResources(), width, height, resourceId);

        // Note that this needs to be the background, not the actual image, otherwise the placeholder
        // won't centre correctly
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(background);
        } else {
            view.setBackground(background);
        }
    }

    /**
     * This function will return a drawable with the gradient radius set to the 1/2 the value of the width or height depending
     * on which is smallest.
     *
     * @param resources used to retrieve the drawable
     * @param width the width of the view this placeholder will be used in
     * @param height the height of hte view this placeholder will be used in
     * @param resourceId the if of the drawable on which to set a gradient radius
     * @return
     */
    public static LayerDrawable inflatePlaceholderLayerDrawable(Resources resources, int width, int height, int resourceId) {
        int gradientRadius = 0;
        if (width > height) {
            gradientRadius = height / 2;
        } else {
            gradientRadius = width / 2;
        }

        // Can't have a gradient radius of 0
        gradientRadius = gradientRadius == 0 ? 1 : gradientRadius;

        final LayerDrawable layer = (LayerDrawable)resources.getDrawable(resourceId);
        final GradientDrawable gradient = (GradientDrawable)layer.getDrawable(0);
        gradient.mutate();
        gradient.setGradientRadius(gradientRadius);

        return layer;
    }

    /**
     * The gradient radius will be set according to which is larger the width or the height
     *
     * @param view on which the background will be set
     * @param resourceId id of the drawable requiring a gradient radius to be set
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("deprecation")
    public static final void setBackground(View view, int resourceId) {
        final int width = view.getMeasuredWidth();
        final int height = view.getMeasuredHeight();

        final Drawable background = inflateGradientDrawable(view.getResources(), width, height, resourceId);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(background);
        } else {
            view.setBackground(background);
        }
    }

    /**
     * This function will return a drawable with the gradient radius set according to whatever is larger the width
     * or height
     * 
     * @param resources used to retrieve the drawable
     * @param width the width of the view this placeholder will be used in
     * @param height the height of hte view this placeholder will be used in
     * @param resourceId the if of the drawable on which to set a gradient radius
     * @return
     */
    public static Drawable inflateGradientDrawable(Resources resources, int width, int height, int resourceId) {
        int gradientRadius = 0;

        if (width > height) {
            gradientRadius = width;
        } else {
            gradientRadius = height;
        }

        // Can't have a gradient radius of 0
        gradientRadius = gradientRadius == 0 ? 1 : gradientRadius;

        return inflateGradientDrawable(resources, gradientRadius, resourceId);
    }

    public static GradientDrawable inflateGradientDrawable(Resources resources, int gradientRadius, int resourceId) {
        final GradientDrawable gradient = (GradientDrawable)resources.getDrawable(resourceId);
        gradient.mutate();
        gradient.setGradientRadius(gradientRadius);
        return gradient;
    }

}
