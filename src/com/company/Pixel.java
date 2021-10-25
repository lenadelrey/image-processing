package com.company;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class Pixel
{
    // RGB color values for this pixel (0-255)
    private int red;        // changed to private Adam Heck and Stephen Preast    4/20/04
    private int green;      //  "           "       "
    private int blue;       //  "           "       "

    /**
     * Constructor for objects of class Pixel
     * Initializes the pixel values;
     */
    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // Accessors and settors added by Adam Heck and Stephen Preast  4/20/04
    public int getRed() { return red; }
    public int getGreen() { return green; }
    public int getBlue() { return blue;}

    public void setRed(int value) { red = value; }
    public void setGreen(int value) { green = value;}
    public void setBlue(int value) { blue = value;}
}

class PixelImage {
    private BufferedImage myImage;
    private int width;
    private int height;

    /**
     * Map this PixelImage to a real image
     *
     * @param bi The image
     */
    public PixelImage(BufferedImage bi) {
        // initialise instance variables
        this.myImage = bi;
        this.width = bi.getWidth();
        this.height = bi.getHeight();
    }

    /**
     * Return the width of the image
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Return the height of the image
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * IGNORE THIS METHOD
     */
    public BufferedImage getImage() {
        return this.myImage;
    }

    /**
     * Return the image's pixel data as an array of Pixels.  The
     * first coordinate is the x-coordinate, so the size of the
     * array is [width][height], where width and height are the
     * dimensions of the array
     *
     * @return The array of pixels
     */
    public Pixel[][] getData() {
        Raster r = this.myImage.getRaster();
        Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
        int[] samples = new int[3];

        for (int row = 0; row < r.getHeight(); row++) {
            for (int col = 0; col < r.getWidth(); col++) {
                samples = r.getPixel(col, row, samples);
                Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
                data[row][col] = newPixel;
            }
        }

        return data;
    }

    public void setData(Pixel[][] data) throws IllegalArgumentException {
        int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
        WritableRaster wr = this.myImage.getRaster();

        if (data.length != wr.getHeight()) {
            throw new IllegalArgumentException("Array size does not match");
        } else if (data[0].length != wr.getWidth()) {
            throw new IllegalArgumentException("Array size does not match");
        }

        for (int row = 0; row < wr.getHeight(); row++) {
            for (int col = 0; col < wr.getWidth(); col++) {
                pixelValues[0] = data[row][col].getRed();
                pixelValues[1] = data[row][col].getGreen();
                pixelValues[2] = data[row][col].getBlue();
                wr.setPixel(col, row, pixelValues);
            }
        }
    }

}
