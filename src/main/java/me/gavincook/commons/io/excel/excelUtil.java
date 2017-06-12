package me.gavincook.commons.io.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel 导入导出工具类
 * 目前支持一个sheet页 2017年6月12日
 * @author lywb
 * @date 2017/6/10 15:22
 * @since 1.0.0
 */
public class excelUtil {


    /**
     * 导入: 从excel到List<Map<String,Object>>
     * @param filePath 文件路径
     * @param keys 字段名称数组，与列对应
     * @return list
     * @throws Exception
     */
    public static List<Map<String,Object>> importTo(String filePath, String[] keys) throws Exception {
        ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> map;

        if(null == keys || 0 == keys.length) {
            throw new Exception("keys can not be null.");
        }

        if (!( filePath.endsWith(".xls") || filePath.endsWith(".xlsx")) ) {
            throw new Exception("incorrect file format.");
        }
        // 读取文件
        FileInputStream fis = null;
        Workbook workbook = null;

        try{
            fis = new FileInputStream(filePath);
            if(filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else if(filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            }

            if (null == workbook){
                throw new Exception("get excel failed.");
            }

            // 获取第一个sheet页
            Sheet sheet = workbook.getSheetAt(0);
            //获取表头行
            Row headRow = sheet.getRow(0);
            //获取表头列数
            int cellNum = headRow.getPhysicalNumberOfCells();

            if( keys.length != cellNum ) {
                throw new Exception("incorrect keys.length or cellNum.");
            }

            //获得数据的总行数
            int totalRowNum = sheet.getLastRowNum();
            Row row;
            Cell cell;
            Object value;

            for (int i=0;i<totalRowNum;i++){
                map = new HashMap<String, Object>();

                row = sheet.getRow(i);
                if(null == row) continue;	// 若该行第一列为空，则默认认为该行就是空行

                // 遍历该行所有列
                for (short j = 0; j < cellNum; j++) {
                    cell = row.getCell(j);
                    if(null == cell) continue;	// 为空时，下一列

                    // 不同的类型，做相应的处理
                    if(Cell.CELL_TYPE_STRING == cell.getCellType()) {
                        value = cell.getStringCellValue();
                    } else if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        value = cell.getNumericCellValue();
                        // 日期类型格式也被认为是数值型，此处判断是否是日期的格式，若为时间，则读取为日期类型
                        if(cell.getCellStyle().getDataFormat() > 0)  {
                            value = cell.getDateCellValue();
                        }
                    } else if(Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
                        value = cell.getBooleanCellValue();
                    } else if(Cell.CELL_TYPE_BLANK == cell.getCellType()) {
                        value = cell.getDateCellValue();
                    } else {
                        throw new Exception("At row: %s, col: %s, unknown type!");
                    }

                    map.put(keys[j], value);
                }
                list.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("analysis excel exception", e);
        } finally {
            if(null != fis) {
                fis.close();
            }
        }
        return list;
    }


}
