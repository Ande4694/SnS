package snsinternaltransfer.sns.utility;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelUtils {

    // kan tage list af alle typer, filnavn/placering på disk, sheet name, og list af data
    public static <T> void writeToExcelInMultiSheets(final String fileName, final String sheetName,
                                                           final List<T> data) {

        // instanciere 3 classer, sætter dem til null
        File file = null;
        OutputStream fos = null;
        XSSFWorkbook workbook = null;



        try {

            // sætter fil navn og placering fra parameter
            file = new File(fileName);
            Sheet sheet = null;

            // hvis filen allerede findes, loades den ind via FileInputStreamer og der laved et nyt "workbook" med den gamle info
            if (file.exists()) {
                workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));

            // ellers oprettes der en blank "workbook"
            } else {
                workbook = new XSSFWorkbook();
            }

            // sheetname sættes fra parameter
            sheet = workbook.createSheet(sheetName);

            // "fieldNames" listen laves og udfyldes af attribute navnet på den datatype der gives
            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());

            // 2 "counters" oprettes
            int rowCount = 0;
            int columnCount = 0;

            // rowCount bliver ++'et, altså starter rowcount på 1
            Row row = sheet.createRow(rowCount++);

            //for hver element der er i "fieldNames" laves og udfyldes en celle i excel arket og cellCount bliver incrimeret
            //Dette laver "header" i excel arket
            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }


            Class<? extends Object> classz = data.get(0).getClass();
            for (T t : data) {

                // row count incrimeres, vi er nu på row 2
                //der skabes en ny række
                row = sheet.createRow(rowCount++);

                // columnCount sættes til 0 igen, så vi følger den rækkefølge af data der står i header
                columnCount = 0;

                //for loop der kører så længe der er elementer tilbage i fieldNames(så det matcher med den data der er i f.eks. "nan" arrayListen)
                for (String fieldName : fieldNames) {

                    // der laves en ny celle
                    Cell cell = row.createCell(columnCount);
                    Method method = null;


                    try {

                        //forsøger at få data fra arrayet der matcher attributen fra "fieldName" og skriver det med første bogstav som stort
                        method = classz.getMethod("get" + capitalize(fieldName));

                        //hvis ikke skriver den det med småt
                    } catch (NoSuchMethodException nme) {
                        method = classz.getMethod("get" + fieldName);
                    }

                    // får værdien i arrayer, og finder typen, så længe værdien ikke er "null"
                    //derefter skrives der til den "cell" man er på, via "cell.setCellValue((TYPE) værdi)
                    Object value = method.invoke(t, (Object[]) null);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        }
                    }

                    // man går til næste linie i excel arket
                    columnCount++;
                }
            }

            //lukker filen
            fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.flush();

          // fanger fejl og lukker filen
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
            }
        }
    }

    //finder headers til vores excel fil
    private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fieldNames.add(fields[i].getName());
        }
        return fieldNames;
    }

    //første bogstav bliver uppercase
    private static String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
