import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.Image;

import javax.imageio.ImageIO;

public class ImageProcessorTest{
      //get the goal bmps
      //the path is relative 
   	private String filePathGoal[] = {"bmptest/goal/1_blue_goal.bmp",
   							 "bmptest/goal/1_gray_goal.bmp",
   							 "bmptest/goal/1_green_goal.bmp",
   							 "bmptest/goal/1_red_goal.bmp",
   							 "bmptest/goal/2_blue_goal.bmp",
   							 "bmptest/goal/2_gray_goal.bmp",
   							 "bmptest/goal/2_green_goal.bmp",
   							 "bmptest/goal/2_red_goal.bmp"};

      //get the result bmps
      //the path is relative 
   	private String filePathResult[] = {"bmptest/result/1_blue_result.bmp",
   							   "bmptest/result/1_gray_result.bmp",
   							   "bmptest/result/1_green_result.bmp",
   							   "bmptest/result/1_red_result.bmp",
   							   "bmptest/result/2_blue_result.bmp",
   							   "bmptest/result/2_gray_result.bmp",
   							   "bmptest/result/2_green_result.bmp",
   							   "bmptest/result/2_red_result.bmp"};

      //test height and width
   	@Test
   	public void testHeightAndWidth() {
   		for (int i = 0; i < 8; ++i) {
   			Image imageGoal = null;
   			Image imageResult = null;

   			String goal = filePathGoal[i];
   			String result = filePathResult[i];

   			File file1 = new File(goal);
   			File file2 = new File(result);
   			try {
   				imageGoal = ImageIO.read(file1);
   				imageResult = ImageIO.read(file2);
			} catch(IOException e) {
				return;
			}  
         //test height and width
   		assertEquals(imageGoal.getHeight(null), imageResult.getHeight(null));
   		assertEquals(imageGoal.getWidth(null), imageResult.getWidth(null));
   		}
   	}

      //test pixels
   	@Test
   	public void testPixel() {
   		for (int i = 0; i < 8; ++i) {
   			Image imageGoal = null;
   			Image imageResult = null;

   			String goal = filePathGoal[i];
   			String result = filePathGoal[i];

   			File file1 = new File(goal);
   			File file2 = new File(result);
   			try {
   				imageGoal = ImageIO.read(file1);
   				imageResult = ImageIO.read(file2);
			} catch(IOException e) {
				return;
			}

			FileInputStream in1 = null;
			FileInputStream in2 = null;
			try {
   				in1 = new FileInputStream(goal);
   				in2 = new FileInputStream(result);
   			} catch(FileNotFoundException e) {
   				return;
   			}


            //the size of pixels
   			int size = imageGoal.getHeight(null) * imageResult.getWidth(null);

   			byte rgb1[] = new byte[size];
   			byte rgb2[] = new byte[size];
   			try {
               //skip the first 54 bytes
   				in1.skip(54);
   				in2.skip(54);

               //read the pixels
   				in1.read(rgb1, 0, size);
   				in2.read(rgb2, 0, size);
   			} catch(IOException e) {
   				return;
   			}
   			assertArrayEquals(rgb1, rgb2);
   		}
   	}
}