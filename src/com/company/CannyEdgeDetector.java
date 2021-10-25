package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class CannyEdgeDetector {
    /**
     * Making thresholding
     * @param img the image to treat
     * @return the image treated
     */
    public BufferedImage makingThresholding(BufferedImage img, double tresholding) {
        int tMax = 40;
        int tMin = 20;
        //tresholding = 80;
        Color myWhite = new Color(255, 255, 255); // Color white
        int white = myWhite.getRGB();

        Color myBlack = new Color(0, 0, 0); // Color white
        int black = myBlack.getRGB();

        int[][] allPixRf = new int[img.getWidth()][img.getHeight()];
        int[][] allPixGf = new int[img.getWidth()][img.getHeight()];
        int[][] allPixBf = new int[img.getWidth()][img.getHeight()];

        for (int i = 2; i < img.getWidth() - 2; i++) {
            for (int j = 2; j < img.getHeight() - 2; j++) {
                if(allPixRf[i][j] > tresholding) {
                    allPixRf[i][j] = 1;
                } else {
                    allPixRf[i][j] = 0;
                }

                if(allPixGf[i][j] > tresholding) {
                    allPixGf[i][j] = 1;
                } else {
                    allPixGf[i][j] = 0;
                }

                if(allPixBf[i][j] > tresholding) {
                    allPixBf[i][j] = 1;
                } else {
                    allPixBf[i][j] = 0;
                }

                if(allPixRf[i][j] == 1 || allPixGf[i][j] == 1 || allPixBf[i][j] == 1) {
                    img.setRGB(i, j, black);
                } else {
                    img.setRGB(i, j, white);
                }
            }
        }


//        for (int y = 0; y < img.getHeight(); y++) {
//            for (int x = 0; x < img.getWidth(); x++) {
//                Color mycolor = new Color(img.getRGB(x, y));
//                if (mycolor.getRGB() < tMin) {
//                    img.setRGB(x, y, black);
//                } else if (mycolor.getRGB() > tMax) {
//                    img.setRGB(x, y, white);
//                }
//            }
//        }

        return img;
    }

    /**
     * Create the sobel filter Sx
     * @return the sobel filter Sx
     */
    private int[][] getSobelFilterX() {
        int [][] sobelX = new int[3][3];

        sobelX[0] = new int[]{-1, 0, 1};
        sobelX[1] = new int[]{-2, 0, 2};
        sobelX[2] = new int[]{-1, 0, 1};

        return sobelX;
    }

    /**
     * Create the sobel filter Sy
     * @return the sobel filter Sy
     */
    private int[][] getSobelFilterY() {
        int [][] sobelY = new int[3][3];

        sobelY[0] = new int[]{-1, -2, -1};
        sobelY[1] = new int[]{0, 0, 0};
        sobelY[2] = new int[]{1, 2, 1};

        return sobelY;
    }

    /**
     * Apply the sobelX filter to image
     * @param img the image
     * @param sobelFilterX the filter to apply
     * @return the final image
     */
    private int[][] getSobelX(BufferedImage img, int[][] sobelFilterX) {
        int height = img.getHeight();
        int width = img.getWidth();

        //Create a new image of the size of the image
        int[][] sobelX = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //Handle case where we're at the borders of the image
                        if ((y == 0 && x == 0 && (i == 0 || j == 0)) || (x == 0 && y == (height - 1) && (i == 2 || j == 0)) ||
                                (y == 0 && x == (width - 1) && (i == 0 || j == 2)) ||
                                (y == (height - 1) && x == (width - 1) && (i == 2 || j == 2)) ||
                                (y == 0 && x != 0 && x != (width - 1) && i == 0) || (x == 0 && y != 0 && y != (height - 1) && j == 0)
                                || (y == (height - 1) && x != 0 && x != (width - 1) && i == 2) ||
                                (x == (width - 1) && y != 0 && y != (height - 1) && j == 2)) {
                            ;
                        } else {
                            //Apply the kernel on pixels around
                            color += img.getRGB(x + (j - 1), y + (i - 1)) * sobelFilterX[i][j];
                        }
                    }
                }
            }
        }
        return sobelX;
    }

    /**
     * Apply the sobelY filter to image
     * @param img the image
     * @param sobelFilterY the filter to apply
     * @return the final image
     */
    private int[][] getSobelY(BufferedImage img, int[][] sobelFilterY) {
        int height = img.getHeight();
        int width = img.getWidth();

        //Create a new image of the size of the image
        int[][] sobelY = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //Handle case where we're at the borders of the image
                        if ((y == 0 && x == 0 && (i == 0 || j == 0)) || (x == 0 && y == (height - 1) && (i == 2 || j == 0)) ||
                                (y == 0 && x == (width - 1) && (i == 0 || j == 2)) ||
                                (y == (height - 1) && x == (width - 1) && (i == 2 || j == 2)) ||
                                (y == 0 && x != 0 && x != (width - 1) && i == 0) || (x == 0 && y != 0 && y != (height - 1) && j == 0)
                                || (y == (height - 1) && x != 0 && x != (width - 1) && i == 2) ||
                                (x == (width - 1) && y != 0 && y != (height - 1) && j == 2)) {
                            ;
                        } else {
                            //Apply the kernel on pixels around
                            color += img.getRGB(x + (j - 1), y + (i - 1)) * sobelFilterY[i][j];
                        }
                    }
                }
                sobelY[y][x] = color;
            }
        }
        return sobelY;
    }

    /**
     * Calculate the angle of the gradients
     * @param sobelX the X sobel result
     * @param sobelY the Y sobel result
     * @param angle the angle tab to fill
     * @param height the height of image
     * @param width the width of image
     * @return the gradient table filled
     */
    private int[][] getGradientAngle(int[][] sobelX, int[][] sobelY, int[][] angle, int height, int width) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                angle[y][x] = (int) ((180 / Math.PI) * Math.atan2((sobelY[y][x]), (sobelX[y][x])));
            }
        }
        return angle;
    }

    /**
     * Making average on the gradient angle tab
     * @param angle the angle tab
     * @param averageAngle the average angle tab to fill
     * @param height the height of image
     * @param width the width of image
     * @return the average angle tab filled
     */
    private int[][] getAverageGradientAngle(int [][]angle, int[][] averageAngle, int height, int width) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((angle[y][x] >= -22.5 && angle[y][x] < 22.5) ||
                        (angle[y][x] >= -202.5 && angle[y][x] < 157.5)) {
                    averageAngle[y][x] = 0;
                }
                else if ((angle[y][x] >= 22.5 && angle[y][x] < 67.5) ||
                        (angle[y][x] >= -157.5 && angle[y][x] < -112.5)) {
                    averageAngle[y][x] = 45;
                }
                else if ((angle[y][x] >= 67.5 && angle[y][x] < 112.5) ||
                        (angle[y][x] >= -112.5 && angle[y][x] < -67.5)) {
                    averageAngle[y][x] = 90;
                }
                else if ((angle[y][x] >= 112.5 && angle[y][x] < 157.5) ||
                        (angle[y][x] >= -67.5 && angle[y][x] < -22.5)) {
                    averageAngle[y][x] = 135;
                }
            }
        }
        return averageAngle;
    }

    /**
     * Keep only local maxima
     * @param img the gradient image
     * @param averageAngle the average table angle
     * @return the image with non maxima suppression
     */
    private BufferedImage deleteNonMaximum(BufferedImage img, int[][] averageAngle) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (averageAngle[y][x] == 0) {
                    if (((x - 1) >= 0 && img.getRGB(x, y) < img.getRGB(x - 1, y)) ||
                            ((x + 1) <= (img.getWidth() - 1) && img.getRGB(x, y) < img.getRGB(x + 1, y))) {
                        img.setRGB(x, y, 0);
                    }
                } else if (averageAngle[y][x] == 45) {
                    if (((x - 1) >= 0 && (y - 1) >= 0 && img.getRGB(x, y) < img.getRGB(x - 1, y - 1)) ||
                            ((x + 1) <= (img.getWidth() - 1) && (y + 1) <= (img.getHeight() - 1) &&
                                    img.getRGB(x, y) < img.getRGB(x + 1, y + 1))) {
                        img.setRGB(x, y, 0);
                    }
                } else if (averageAngle[y][x] == 90) {
                    if (((y - 1) >= 0 && img.getRGB(x, y) < img.getRGB(x, y - 1)) ||
                            ((y + 1) <= (img.getHeight() - 1) && img.getRGB(x, y) < img.getRGB(x, y + 1))) {
                        img.setRGB(x, y, 0);
                    }
                } else if (averageAngle[y][x] == 135) {
                    if (((x - 1) >= 0 && (y + 1) <= (img.getHeight() - 1) && img.getRGB(x, y) < img.getRGB(x - 1, y + 1)) ||
                            ((x + 1) <= (img.getWidth() - 1) && (y - 1) >= 0 && img.getRGB(x, y) < img.getRGB(x + 1, y - 1))) {
                        img.setRGB(x, y, 0);
                    }
                }
            }
        }
        return img;
    }

    /**
     * calculate magnitude of image
     * @param img the image to treat
     * @param sobelX the sobel X image
     * @param sobelY the sobel Y image
     * @return the image treated
     */
    private BufferedImage calculateMagnitude(BufferedImage img, int[][] sobelX, int[][] sobelY) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int magnitude = (int) Math.sqrt(pow(sobelX[y][x], 2) + pow(sobelY[y][x], 2));
                img.setRGB(x, y, magnitude);
            }
        }

        return img;
    }

    /**
     * Find the gradients of the image
     * @param img the image to treat
     */
    public BufferedImage findGradients(BufferedImage img) {
        System.out.println("Action: find the gradients of image.. ");
        //Get the sobel filter
        int[][] sobelFilterX = getSobelFilterX();
        int[][] sobelFilterY = getSobelFilterY();

        //Create two new image of the size of the image
        int[][] sobelX = getSobelX(img, sobelFilterX);
        int[][] sobelY = getSobelY(img, sobelFilterY);

        //Mark large magnitude
        calculateMagnitude(img, sobelX, sobelY);
        System.out.println("OK.");

        System.out.println("Action: non-maximum suppression.. ");
        int[][] angle = new int[img.getHeight()][img.getWidth()];
        getGradientAngle(sobelX, sobelY, angle, img.getHeight(), img.getWidth());

        int[][] averageAngle = new int[img.getHeight()][img.getWidth()];
        getAverageGradientAngle(angle, averageAngle, img.getHeight(), img.getWidth());

        deleteNonMaximum(img, averageAngle);
        System.out.println("OK.");
        return img;
    }

    /**
     * Apply gaussian filter to the image
     * @param img the image
     * @return the image after applying gaussian filter
     */
    public BufferedImage gaussianFilter(BufferedImage img, double k) {
        double[][] kernel = gaussianKernel(k);

        System.out.println("Action: apply gaussian filter.. ");

        int height = img.getHeight();
        int width = img.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double r = 0;
                double g = 0;
                double b = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //Handle case where we're at the borders of the image
                        if ((y == 0 && x == 0 && (i == 0 || j == 0)) || (x == 0 && y == (height - 1) && (i == 2 || j == 0)) ||
                                (y == 0 && x == (width - 1) && (i == 0 || j == 2)) ||
                                (y == (height - 1) && x == (width - 1) && (i == 2 || j == 2)) ||
                                (y == 0 && x != 0 && x != (width - 1) && i == 0) || (x == 0 && y != 0 && y != (height - 1) && j == 0)
                                || (y == (height - 1) && x != 0 && x != (width - 1) && i == 2) ||
                                (x == (width - 1) && y != 0 && y != (height - 1) && j == 2)) {

                        } else {
                            //Apply the kernel on pixels around
                            Color c = new Color(img.getRGB(x + (j - 1), y + (i - 1)));
                            r += c.getRed() * kernel[i][j];
                            g += c.getGreen() * kernel[i][j];
                            b += c.getBlue() * kernel[i][j];
                        }
                    }
                }
                //Change the color of the pixel
                img.setRGB(x, y, new Color((int)r, (int)g, (int)b).getRGB());
            }
        }
        System.out.println("OK.");
        return img;
    }

    /**
     * Calculate gaussian kernel 3x3 with sigma = 1
     * @return the gaussian kernel
     */
    private double[][] gaussianKernel(double k) {
        System.out.println("Action: calculate gaussian kernel.. ");
        double sigma = k;
        double[][] kernel = new double[3][3];

        double mean = 3 / 2;

        double sum = 0.0;

        for (int x = 0; x < 3; ++x)
            for (int y = 0; y < 3; ++y) {

                kernel[x][y] = exp(-0.5 * (pow((x - mean) / sigma, 2.0) + pow((y - mean) / sigma, 2.0)))
                        / (2 * Math.PI * sigma * sigma);

                sum += kernel[x][y];
            }

        System.out.println("OK.");
        return kernel;
    }

    /**
     * This function convert image to grayscale
     * @param myImage the image to convert
     */
    BufferedImage grayscale(BufferedImage myImage) {
        System.out.println("Action: convert to grayscale.. ");
        int rgb;
        int a;
        int r;
        int g;
        int b;
        int avg;

        int height = myImage.getHeight();
        int width = myImage.getWidth();

        //For each pixel of image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rgb = myImage.getRGB(x, y);

                //We take the value of R, G, B and A
                a = (rgb >> 24) & 0xff;
                r = (rgb >> 16) & 0xff;
                g = (rgb >> 8) & 0xff;
                b = rgb & 0xff;

                //We make average of R, G and B
                avg = (r + g + b) / 3;

                //We replace R, G and B by the average we calculate before
                rgb = (a << 24) | (avg << 16) | (avg << 8) | avg;

                myImage.setRGB(x, y, rgb);
            }
        }
        System.out.println("OK.");
        return myImage;
    }

    /**
     * Read image
     * @param path the path of image to read
     * @return the image readed
     */
//    private static BufferedImage readImage(String path) {
//        BufferedImage myImage = null;
//        File myFile;
//
//        //Try to read the image
//        try {
//            myFile = new File(path);
//            myImage = ImageIO.read(myFile);
//        } catch(IOException e){
//            System.out.println("Error: can't read the image.");
//            System.exit(1);
//        }
//        return (myImage);
//    }

    /**
     * Write an image
     * @param myImage the image to write
     */
    public void writeImage(BufferedImage myImage) {
        File myFile;

        try {
            myFile = new File("./output.png");
            ImageIO.write(myImage, "png", myFile);
        } catch (IOException e) {
            System.out.println("Error: couldn't save image.");
            System.exit(1);
        }
    }

//    public static void main(String args[]) throws IOException {
//        BufferedImage myImage;
//
//        //Checking if user gives image path in args
//        if (args.length != 1) {
//            System.out.println("Error: Add the image path in args.");
//            System.exit(1);
//        }
//
//        //Read image
//        myImage = readImage(args[0]);
//        //Convert image to grayscale
//        myImage = grayscale(myImage);
//        //Calculate gaussian kernel
//        myImage = gaussianFilter(myImage);
//        //Finding gradients and making non-maximum suppression
//        myImage = findGradients(myImage);
//        //Making thresholding
//        //myImage = makingThresholding(myImage);
//        //We save final image
//        writeImage(myImage);
//
//        System.out.println("Image saved successfully as 'output.png'");
//    }
}
