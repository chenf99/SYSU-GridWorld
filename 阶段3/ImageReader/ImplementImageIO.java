import imagereader.IImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImplementImageIO implements IImageIO{
	public Image myRead(String filePath) throws IOException{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
		byte[] header = new byte[14];
		byte[] info = new byte[40];
		Image image = null;
		
		in.read(header);
		in.read(info);

		int width = (int)((info[4] & 0xFF) 
				| ((info[5] & 0xFF)<<8) 
				| ((info[6] & 0xFF)<<16) 
				| ((info[7] & 0xFF)<<24));

		int height = (int)((info[8] & 0xFF) 
				| ((info[9] & 0xFF)<<8) 
				| ((info[10] & 0xFF)<<16) 
				| ((info[11] & 0xFF)<<24));

		//compute the size of image
		int sizeImage = (int)((info[20] & 0xFF) 
				| ((info[21] & 0xFF)<<8) 
				| ((info[22] & 0xFF)<<16) 
				| ((info[23] & 0xFF)<<24));

		int bicount = (((int)info[15] & 0xFF) << 8)
				| (int)info[14] & 0xFF;

		if (bicount == 24) {
			//compute the empty byte
			int npad = (sizeImage / height) - width * 3;
			if (npad == 4) {
				npad = 0;
			}

			//compute the size of pixel
			int size = (width + npad) * 3 * height;

			byte[] rgb;
			//save rgb data
			if (npad != 0) {
				rgb = new byte[size];
			}
			else {
				rgb = new byte[sizeImage];
			}


			//read the rgb data
			in.read(rgb, 0, size);
			int[] pixel = new int[height * width];

			//get the pixel
			int index = 0;
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					pixel[width * (height - i - 1) + j] = 
					(255 & 0xFF) << 24
					| (((int)rgb[index + 2] & 0xFF) << 16)
					| (((int)rgb[index + 1] & 0xFF) << 8)
					| (int)rgb[index] & 0xFF;
					index += 3;
				}
				index += npad;
			}

			image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
				width, height, pixel, 0, width));
			in.close();
		}
		return image;
	}
	public Image myWrite(Image image, String filePath) throws IOException {
		int width = image.getWidth(null);
		int height = image.getHeight(null);

		//transfer image to bufferedimage
		BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = im.getGraphics();
		g.drawImage(image, 0, 0, null);

		File file = new File(filePath + ".bmp");
		ImageIO.write(im, "bmp", file);
		return image;
	}
}
