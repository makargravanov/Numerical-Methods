package Formatting.Interface;

import java.util.ArrayList;
import java.util.List;

public interface TableInterface {
    public default void drawCustomTable(List<?> list, List<String> headers) throws IllegalArgumentException {
        if(list.isEmpty()){
            throw new IllegalArgumentException("List cannot be empty");
        }
        if(headers.isEmpty()){
            throw new IllegalArgumentException("Headers list cannot be empty");
        }
        headers.addFirst("i");
        int columns = headers.size();
        List<List<Object>> data = new ArrayList<>();
        for(int i=0,k=0;i<=(list.size()/columns+2);i++){//+2 только для SimpsonIntegration
            List <Object> l = new ArrayList<>();
            l.add(i+1);
            for(int j=0;j<columns-1;j++){
                try {
                    l.add(list.get(k));
                }catch (IndexOutOfBoundsException e){
                    l.add("пустая ячейка");
                };
                k++;
            }
            data.add(l);
        }


        int[] columnWidths = new int[columns];
        for (int i = 0; i < columns; i++) {
            int maxWidth = headers.get(i).length();
            for (List<Object> row : data) {
                if (row.get(i) != null && String.valueOf(row.get(i)).length() > maxWidth) {
                    maxWidth = String.valueOf(row.get(i)).length();
                }
            }
            columnWidths[i] = maxWidth;
        }

        drawHorizontalLine(columnWidths);
        drawRow(headers.toArray(new String[0]), columnWidths);
        drawHorizontalLine(columnWidths);

        for (List<Object> row : data) {
            drawRow(row.toArray(), columnWidths);
            drawHorizontalLine(columnWidths);
        }
    }

    private void drawHorizontalLine(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+" + "-".repeat(width + 2));
        }
        System.out.println("+");
    }

    private void drawRow(Object[] rowData, int[] columnWidths) {
        for (int i = 0; i < rowData.length; i++) {
            String cellValue = (rowData[i] != null) ? String.format("| %" + columnWidths[i] + "s ", rowData[i]) : "|";
            System.out.print(cellValue);
        }
        System.out.println("|");
    }
}
