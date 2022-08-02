
package worker;

import java.io.*;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class IOMaster {

    private IOMaster() {
    }
    public static String readUTF8Text(String file) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        FileInputStream fi = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fi, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String sR = "", sNewLine;

        while ((sNewLine = br.readLine()) != null) {
            sR += sNewLine + "\n";
        }
        fi.close();
        return sR;
    }

    public static void writeUTF8Text(String file, String text) throws FileNotFoundException, UnsupportedEncodingException, IOException, Exception {
        writeUTF8Text(file, text, false);
    }

    public static void writeUTF8Text(String file, String text, boolean append) throws FileNotFoundException, UnsupportedEncodingException, IOException, Exception {
        String tmpFileName = file + 0;
        if (new File(file).exists()) {
            int i = 0;
            while (new File(tmpFileName).exists()) {
                i++;
                tmpFileName = file + i;
            }
            if (!new File(file).renameTo(new File(tmpFileName))) {
                throw new Exception();
            }
        }
        try {
            FileOutputStream fo = new FileOutputStream(file, append);
            OutputStreamWriter osw = new OutputStreamWriter(fo, "utf-8");
            osw.write(text);
            osw.flush();
            fo.close();
            try {
                new File(tmpFileName).delete();
            } catch (Exception e) {
            }
        } catch (IOException iOException) {
            new File(file).delete();
            new File(tmpFileName).renameTo(new File(file));
        }
    }

    public static void writeObject(String file, Object obj) throws Exception {
        String tmpFileName = file + 0;
        if (new File(file).exists()) {
            int i = 0;
            while (new File(tmpFileName).exists()) {
                i++;
                tmpFileName = file + i;
            }
            if (!new File(file).renameTo(new File(tmpFileName))) {
                throw new Exception();
            }
        }
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(obj);
            oo.flush();
            fo.close();
            try {
                new File(tmpFileName).delete();
            } catch (Exception e) {
            }
        } catch (IOException iOException) {
            new File(file).delete();
            new File(tmpFileName).renameTo(new File(file));
        }
    }

    public static Object readObject(String file) throws Exception {
        Object obj;
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);
        obj = oi.readObject();
        fi.close();
        return obj;
    }

    public static Object readObjectFromStream(InputStream fileStream) throws Exception {
        Object obj;
        ObjectInputStream oi = new ObjectInputStream(fileStream);
        obj = oi.readObject();
        fileStream.close();
        return obj;
    }
}
