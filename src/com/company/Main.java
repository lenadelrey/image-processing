package com.company;


import histogram.Transformation;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Main {

    private JPanel jPanel = new JPanel();
    private JTextField input = new JTextField(5);
    private JTextField input1 = new JTextField(5);
    private JLabel jLabel = new JLabel();
    private JLabel image = new JLabel();
    private JLabel imageHistogram = new JLabel();
    private JFrame jFrame = new JFrame("Laba 4");
    private BufferedImage bufferedImage;
    private BufferedImage bufferedImageHistogram;
    private boolean visibleButtonsForImage = false;
    private JButton jButton = new JButton("Explore");
    private JButton save = new JButton("Save");
    private JButton grey = new JButton("Greyscale");
    private JButton gaussianFilter = new JButton("GaussianFilter");
    private JButton cannyDetector = new JButton("CannyEdgeDetector");
    private JButton histogram = new JButton("CreateHistogram");
    private JButton a = new JButton("A");
    private JButton b = new JButton("Б");
    private JButton c = new JButton("В");
    private JButton d = new JButton("Г");
    private JButton ee = new JButton("Д");
    private JButton f = new JButton("Е");
    private JButton g = new JButton("Ж");
    private JButton h = new JButton("З");
    private JButton i = new JButton("И");
    private JButton j = new JButton("К");

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        save.setVisible(visibleButtonsForImage);
        gaussianFilter.setVisible(visibleButtonsForImage);
        input.setVisible(visibleButtonsForImage);
        input1.setVisible(visibleButtonsForImage);
        cannyDetector.setVisible(visibleButtonsForImage);
        histogram.setVisible(visibleButtonsForImage);
        grey.setVisible(visibleButtonsForImage);
        a.setVisible(visibleButtonsForImage);
        b.setVisible(visibleButtonsForImage);
        c.setVisible(visibleButtonsForImage);
        d.setVisible(visibleButtonsForImage);
        ee.setVisible(visibleButtonsForImage);
        f.setVisible(visibleButtonsForImage);
        g.setVisible(visibleButtonsForImage);
        h.setVisible(visibleButtonsForImage);
        i.setVisible(visibleButtonsForImage);
        j.setVisible(visibleButtonsForImage);

        jButton.addActionListener(new ExploreButtonListener());
        save.addActionListener(new SaveButtonListener());
        gaussianFilter.addActionListener(new GaussianFilterButtonListener());
        cannyDetector.addActionListener(new CannyEdgeDetectorButtonListener());
        histogram.addActionListener(new HistogramButtonListener());
        grey.addActionListener(new RgbToGrayButtonListener());
        a.addActionListener(new AButtonListener());
        b.addActionListener(new BButtonListener());
        c.addActionListener(new CButtonListener());
        d.addActionListener(new DButtonListener());
        ee.addActionListener(new EButtonListener());
        f.addActionListener(new FButtonListener());
        g.addActionListener(new GButtonListener());
        h.addActionListener(new HButtonListener());
        i.addActionListener(new IButtonListener());
        j.addActionListener(new JButtonListener());

        jPanel.add(jButton);
        jPanel.add(save);
        jPanel.add(gaussianFilter);
        jPanel.add(input);
        jPanel.add(input1);
        jPanel.add(cannyDetector);
        jPanel.add(grey);
        jPanel.add(histogram);
        jPanel.add(a);
        jPanel.add(b);
        jPanel.add(c);
        jPanel.add(d);
        jPanel.add(ee);
        jPanel.add(f);
        jPanel.add(g);
        jPanel.add(h);
        jPanel.add(i);
        jPanel.add(j);
        jPanel.add(image);

        jFrame.add(jPanel);

        jFrame.setVisible(true);
        jFrame.setSize(800, 800);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public class ExploreButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fileDialog = new FileDialog(jFrame, "Explore", FileDialog.LOAD);
            fileDialog.setVisible(true);
            //jLabel.setText(fileDialog.getFile());
            File file = new File(fileDialog.getFile());
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            image.setIcon(new ImageIcon(bufferedImage));
            gaussianFilter.setVisible(true);
            save.setVisible(true);
            input.setVisible(true);
            input1.setVisible(true);
            cannyDetector.setVisible(true);
            histogram.setVisible(true);
            grey.setVisible(true);
            a.setVisible(true);
            b.setVisible(true);
            c.setVisible(true);
            d.setVisible(true);
            ee.setVisible(true);
            f.setVisible(true);
            g.setVisible(true);
            h.setVisible(true);
            i.setVisible(true);
            j.setVisible(true);
        }
    }

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fileDialog = new FileDialog(jFrame, "Save", FileDialog.SAVE);
            fileDialog.setVisible(true);
            File outputfile = new File(fileDialog.getFile());
            try {
                ImageIO.write(bufferedImage, ".jpg", outputfile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private class RgbToGrayButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//            ColorConvertOp op = new ColorConvertOp(cs, null);
//            bufferedImage = op.filter(bufferedImage, null);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            int rgb, a, r, g, b, avg;
            int height = img.getHeight();
            int width = img.getWidth();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    rgb = img.getRGB(x, y);
                    a = (rgb >> 24) & 0xff;
                    r = (rgb >> 16) & 0xff;
                    g = (rgb >> 8) & 0xff;
                    b = rgb & 0xff;
                    avg = (r + g + b) / 3;

                    rgb = (a << 24) | (avg << 16) | (avg << 8) | avg;
                    img.setRGB(x, y, rgb);
                }
            }
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    private class GaussianFilterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                GaussianFilter filter = new GaussianFilter(Float.parseFloat(input.getText()), Integer.parseInt(input1.getText()));
                filter.filter(bufferedImage, bufferedImage);
                image.setIcon(new ImageIcon(bufferedImage));
            } catch (NumberFormatException exception) {
                return;
            }
        }
    }

    public class CannyEdgeDetectorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Canny canny = new Canny();
            BufferedImage img = canny.process(bufferedImage, Double.parseDouble(input.getText()), Double.parseDouble(input1.getText()), 4);

            image.setIcon(new ImageIcon(img));
            bufferedImage = img;
        }
    }

    private static BufferedImage convertTo3ByteBGRType(BufferedImage image) {
        BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        convertedImage.getGraphics().drawImage(image, 0, 0, null);
        return convertedImage;
    }

    public static BufferedImage mat2Img(Mat mat) {
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        mat.get(0, 0, data);
        return image;
    }

    public static Mat img2Mat(BufferedImage img) {
        img = convertTo3ByteBGRType(img);
        Mat mat = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }

    public class HistogramButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

           // String filename = "C:\\Users\\lenka\\IdeaProjects\\laba_4\\1.png";
            //Mat src = Imgcodecs.imread(filename);
            Mat src = img2Mat(bufferedImage);
            if (src.empty()) {
                System.err.println("Cannot read image");
                System.exit(0);
            }
            List<Mat> bgrPlanes = new ArrayList<>();
            Core.split(src, bgrPlanes);
            int histSize = 256;
            float[] range = {0, 256}; //the upper boundary is exclusive
            MatOfFloat histRange = new MatOfFloat(range);
            boolean accumulate = false;
            Mat bHist = new Mat();
            Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), histRange, accumulate);
            int histW = 512, histH = 400;
            int binW = (int) Math.round((double) histW / histSize);
            Mat histImage = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));
            Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
            float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
            bHist.get(0, 0, bHistData);
            for (int i = 1; i < histSize; i++) {
                Imgproc.line(histImage, new org.opencv.core.Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
                        new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar(255, 255, 255), 2);
            }
            bufferedImageHistogram = mat2Img(histImage);
            imageHistogram.setIcon(new ImageIcon(bufferedImageHistogram));
            JPanel newJPanel = new JPanel();
            newJPanel.add(imageHistogram);
            JFrame newJframe = new JFrame();
            newJframe.add(newJPanel);
            newJframe.setVisible(true);
            newJframe.setSize(bufferedImageHistogram.getWidth() + 100, bufferedImageHistogram.getHeight() + 100);
            imageHistogram.setVisible(true);
        }
    }

    public static BufferedImage convertImage(BufferedImage img, int imgProc) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = Transformation.bufferedImageToMat(img);
        Mat new_mat = new Mat();
        Imgproc.cvtColor(mat, new_mat, imgProc);
        BufferedImage inputImage = Transformation.matToBufferedImage(new_mat);
        return inputImage;
    }

    public class AButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
                BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
                Mat mGray = Transformation.bufferedImageToMat(greyImage);
                int x = Integer.parseInt(input.getText());
                //int x = 25;
                double[] arr;
                Mat mat;
                mat = mGray.clone();
                for (int i = 0; i < mat.rows(); i++) {
                    for (int j = 0; j < mat.cols(); j++) {
                        arr = mat.get(i, j);
                        if (arr[0] < x) {
                            arr[0] = 0;
                        } else {
                            arr[0] = 255;
                        }
                        mat.put(i, j, arr);
                    }
                }
                img = Transformation.matToBufferedImage(mat);
                bufferedImage = img;
                image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class BButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int x1 = Integer.parseInt(input.getText());
            int x2 = Integer.parseInt(input1.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    if (arr[0] > x1 && arr[0] < x2) {
                        arr[0] = 255;
                    } else {
                        arr[0] = 0;
                    }
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class CButtonListener implements ActionListener {        //в
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int x1 = Integer.parseInt(input.getText());
            int x2 = Integer.parseInt(input1.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    if (arr[0] > x1 && arr[0] < x2) {
                        arr[0] = 255;
                    }
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class DButtonListener implements ActionListener {        //г     ??
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int x = Integer.parseInt(input.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mGray.get(i, j);
                    if (arr[0] > x) {
                        arr[0] = 255;
                    }
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class EButtonListener implements ActionListener {            //д
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            Mat mat;
            mat = mGray.clone();
            double[] arr;
            arr = mat.get(0,0);
            double min = arr[0];
            double max = arr[0];
            for(int i = 0; i < mat.rows(); i++){
                for(int j = 0; j < mat.cols(); j++){
                    arr = mat.get(i, j);
                    if(min > arr[0]){
                        min = arr[0];

                    }
                    if(max < arr[0]){
                        max = arr[0];
                    }
                }
            }
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    arr[0] = 255 * (arr[0] - min) / (max - min);
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class FButtonListener implements ActionListener {        //е
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
//            int x1 = Integer.parseInt(input.getText());
//            int x2 = Integer.parseInt(input1.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            arr = mat.get(0,0);
            double min = arr[0];
            double max = arr[0];
            for(int i = 0; i < mat.rows(); i++){
                for(int j = 0; j < mat.cols(); j++){
                    arr = mat.get(i, j);
                    if(min > arr[0]){
                        min = arr[0];
                    }
                    if(max < arr[0]){
                        max = arr[0];
                    }
                }
            }
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    arr[0] = 255 - (255 * (arr[0] - min) / (max - min));
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class GButtonListener implements ActionListener {        //ж
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int x1 = Integer.parseInt(input.getText());
            int x2 = Integer.parseInt(input1.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    if (arr[0] < x1) {
                        arr[0] = 0;
                    } else if
                    (arr[0] > x2) {
                        arr[0] = 0;
                    } else {
                        arr[0] = 255 * (arr[0] - x1) / (x2 - x1);
                    }
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class HButtonListener implements ActionListener {        //з
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int x1 = Integer.parseInt(input.getText());
            int x2 = Integer.parseInt(input1.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    if (arr[0] < x1) {
                        arr[0] = 255;
                    } else if
                    (arr[0] > x2) {
                        arr[0] = 255;
                    } else {
                        arr[0] = 255 - 255 * (arr[0] - x1) / (x2 - x1);
                    }
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class IButtonListener implements ActionListener {        //и
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int x1 = Integer.parseInt(input.getText());
            int x2 = Integer.parseInt(input1.getText());
            double[] arr;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    if (arr[0] < x1) {
                        arr[0] = arr[0];
                    } else if
                    (arr[0] > x2) {
                        arr[0] = arr[0];
                    } else {
                        arr[0] = 255 * (arr[0] - x1) / (x2 - x1);
                    }
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

    public class JButtonListener implements ActionListener {        //к
        @Override
        public void actionPerformed(ActionEvent e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("deadinside.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedImage greyImage = convertImage(img, Imgproc.COLOR_RGB2GRAY);
            Mat mGray = Transformation.bufferedImageToMat(greyImage);
            int n = Integer.parseInt(input.getText());
            double[] arr;
            int dim = (int) Math.floor(256 / n);
            int x1 = 0;
            int x2 = dim - 1;
            Mat mat;
            mat = mGray.clone();
            for (int i = 0; i < mat.rows(); i++) {
                for (int j = 0; j < mat.cols(); j++) {
                    arr = mat.get(i, j);
                    int pldim = 0;
                    while (arr[0] > dim) {
                        arr[0] = arr[0] - dim;
                        pldim++;
                    }
                    arr[0]++;
                    arr[0] = 255 - 255 * (arr[0] - x1) / (x2 - x1);
                    while (pldim > 0) {
                        arr[0] = arr[0] + dim;
                        pldim--;
                    }
                  arr[0]=(int)((arr[0] - (dim)*Math.floor(arr[0]/dim)) * 255/dim);
                    mat.put(i, j, arr);
                }
            }
            img = Transformation.matToBufferedImage(mat);
            bufferedImage = img;
            image.setIcon(new ImageIcon(bufferedImage));
        }
    }

}