import imagereader.IImageProcessor;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class ImplementImageProcessor implements IImageProcessor{
	//show red bmps
	public Image showChanelR(Image sourceImage) {
		Image image = Toolkit.getDefaultToolkit().createImage(
			new FilteredImageSource(sourceImage.getSource(), new RedFilter()));
		return image;
	}
	//show green bmps
	public Image showChanelG(Image sourceImage) {
		Image image = Toolkit.getDefaultToolkit().createImage(
			new FilteredImageSource(sourceImage.getSource(), new GreenFilter()));
		return image;
	}
	//show blue bmps
	public Image showChanelB(Image sourceImage) {
		Image image = Toolkit.getDefaultToolkit().createImage(
			new FilteredImageSource(sourceImage.getSource(), new BlueFilter()));
		return image;
	}
	//show gray bmps
	public Image showGray(Image sourceImage) {
		Image image = Toolkit.getDefaultToolkit().createImage(
			new FilteredImageSource(sourceImage.getSource(), new GrayFilter()));
		return image;
	}
}

//red filter
class RedFilter extends RGBImageFilter{
	public RedFilter() {
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		return (rgb & 0xFFFF0000);
	}
}

//green filter
class GreenFilter extends RGBImageFilter{
	public GreenFilter() {
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		return (rgb & 0xFF00FF00);
	}
}

//blue filter
class BlueFilter extends RGBImageFilter{
	public BlueFilter() {
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		return (rgb & 0xFF0000FF);
	}
}

//gray filter
class GrayFilter extends RGBImageFilter{
	public GrayFilter() {
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb) {
		//I = 0.299 * R + 0.587 * G + 0.114 *B
		int gray = (int)(((rgb & 0x00FF0000) >> 16) * 0.299 + 
						((rgb & 0x0000FF00) >> 8) * 0.587 + 
						(rgb & 0x000000FF) * 0.144);
		return (rgb & 0xFF000000) + (gray << 16) + (gray << 8) + gray;
	}
}