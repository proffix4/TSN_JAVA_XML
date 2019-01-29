package tsn.java.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

public class App1 {  // ������ � XML-�������

    public static void main(String[] args) {
        try {
            // ������������� ������
            String FileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator") + "tsn_data.xml";
            Properties p = new Properties(); // 
            File f = new File(FileName);
            if (f.exists() == false) {
                f.createNewFile();
            } else {
                p.loadFromXML(new FileInputStream(FileName));
            }

            // ����� ������ � ������� �� �����
            System.out.println("Laboratory work");
            System.out.println("Task: MAX <> MIN");

            // �������� �������� ������ (��������� �������) � ����� �� �� �����
            final int r = 4;
            final int c = 5;
            int m[][] = new int[r][c];
            int k;
            System.out.println("Matrix:");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    k = (int) Math.round(Math.random() * 9);
                    m[i][j] = k;
                    System.out.print(k + " ");
                }
                System.out.println("");
            }

            // ���������� ��������� �������� ������� 
            int min = m[0][0], max = m[0][0], maxi = 0, maxj = 0, mini = 0, minj = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    k = m[i][j];
                    if (k > max) {
                        max = k;
                        maxi = i;
                        maxj = j;
                    }
                    if (k < min) {
                        min = k;
                        mini = i;
                        minj = j;
                    }
                }
            }
            k = m[maxi][maxj];
            m[maxi][maxj] = m[mini][minj];
            m[mini][minj] = k;

            // ���������� ������������ ������ ������� � XML-����
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    p.put("m" + i + j, String.valueOf(m[i][j]));
                }
            }
            p.storeToXML(new FileOutputStream(FileName), new Date().toString());

            // ���������� ������ ��������� ������� �� XML-�����
            System.out.println("New matrix:");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    p.put("m" + i + j, String.valueOf(m[i][j]));
                    System.out.print(p.getProperty("m" + i + j, "?") + " ");
                }
                System.out.println("");
            }

        } catch (Exception e) {
            System.err.println("Error working with XML-file!"); // ������� ��������� �� ������            
        }
    }
}
