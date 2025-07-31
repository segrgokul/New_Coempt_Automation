package scevtPageModules;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Scevt_RC_CreateAndWriteExcel {

	 static XSSFWorkbook workbook = new XSSFWorkbook();
	 static XSSFWorkbook workbook1 = new XSSFWorkbook();
     // Get the sheet by name
     static XSSFSheet sheet = workbook.createSheet();
	
     static XSSFSheet sheet1 = workbook1.createSheet();
    public static void scevtWriteExcelData(int pageno,String name,String dob,String gender ,String chart) throws IOException {
  
    	                // Create a header row if the file is newly created
    	                Row datasRow = sheet.createRow(0);
    	      
//    	                since the serialNo is not need i just this below one to match extact no of columns
    	                
//    	                String[] datavalues = { serialNo,studentRegno,studentSemester, subjectNames,subjectCode,credit,type,st,qt,as,mte,emptyColumn1,emptyColumn2,emptyColumn3,to40,endSemExam
//    	                		,to100,totalMarks,grade};
          
    	                String[] datavalues = {name,dob,gender,chart};

    	                
    	                for (int i = 0; i < datavalues.length; i++) {
    	                	datasRow.createCell(i).setCellValue(datavalues[i]);
    	                }
    	            
    	                XSSFRow firstrow = sheet.getRow(0);
    	        	    if (firstrow == null) {
    	        	        firstrow = sheet.createRow(0);
    	        	    }
    	  
//    	        	  since seral no is no need i just commented it  
						firstrow.createCell(0).setCellValue("Page No");	
    	        	   firstrow.createCell(1).setCellValue("Name");
    	        	   
    	        	   firstrow.createCell(2).setCellValue("DOB");
    	        	   
    	        	    firstrow.createCell(3).setCellValue("Gender");
    	          
    	        		firstrow.createCell(4).setCellValue("Chart");
    	        	//	firstrow.createCell(5).setCellValue("Dummydata"); 
    	      
    	        	
    	            // Create a new row based on the rowIndex from the loop
    	        		  Row Datarow = sheet.createRow(pageno); 

    	            // Write each value in the correct column
    	     //       Datarow.createCell(0).setCellValue(serialNo);
    	      //      Datarow.createCell(1).setCellValue(subjectNames);

    	        		  System.out.println("-------------------------------------------------------------------------");
    	        		     System.out.println("Written: " + name + " in row " + pageno);		  
	        		     
    	        //since sr.no is not need i commented it		     
    	        //     Datarow.createCell(0).setCellValue(serialNo);
    	            Datarow.createCell(0).setCellValue(pageno);
    	            
    	            Datarow.createCell(1).setCellValue(name);
    	            
    	        	Datarow.createCell(2).setCellValue(dob);
    	            
    	            Datarow.createCell(3).setCellValue(gender);
    	            
    	//            Datarow.createCell(5).setCellValue(extraSubjectCode);// skipped because of extra code

    	            Datarow.createCell(4).setCellValue(chart);

    	   
        	        System.out.println("-------------------------------------------------------------------------"); 
    	            // Write changes back to file
    	        

    	       

  
           writeExcelDatas();

			 
    }
		
    public static void scevtWriteExcelData1(int pageno,String studentName,String dob,String gender,String chart,String insurance,String provider) throws IOException {
    	  
        // Create a header row if the file is newly created
        Row datasRow = sheet1.createRow(0);

//        since the serialNo is not need i just this below one to match extact no of columns
        
//        String[] datavalues = { serialNo,studentRegno,studentSemester, subjectNames,subjectCode,credit,type,st,qt,as,mte,emptyColumn1,emptyColumn2,emptyColumn3,to40,endSemExam
//        		,to100,totalMarks,grade};

        String[] datavalues1 = {studentName,dob,gender,chart,insurance};

        
        for (int i = 0; i < datavalues1.length; i++) {
        	datasRow.createCell(i).setCellValue(datavalues1[i]);
        }
    
        XSSFRow firstrow1 = sheet1.getRow(0);
	    if (firstrow1 == null) {
	        firstrow1 = sheet1.createRow(0);
	    }

//	  since seral no is no need i just commented it  
		firstrow1.createCell(0).setCellValue("S.No");	

	   
	   firstrow1.createCell(1).setCellValue("studentNames");
		
		firstrow1.createCell(2).setCellValue("DOB");
		 
		firstrow1.createCell(3).setCellValue("gender");
		
		firstrow1.createCell(4).setCellValue("chart");
		firstrow1.createCell(5).setCellValue("insurance");
		firstrow1.createCell(6).setCellValue("provider");
    // Create a new row based on the rowIndex from the loop
		  Row Datarow1 = sheet1.createRow(pageno); 

    // Write each value in the correct column
//       Datarow.createCell(0).setCellValue(serialNo);
//      Datarow.createCell(1).setCellValue(subjectNames);

		  System.out.println("-------------------------------------------------------------------------");
		     System.out.println("Written: " + studentName + "and DOB "+ dob +" in row " + pageno);		  
	     
//since sr.no is not need i commented it		     
//     Datarow.createCell(0).setCellValue(serialNo);
    Datarow1.createCell(0).setCellValue(pageno);
    
    Datarow1.createCell(1).setCellValue(studentName);
    
    Datarow1.createCell(2).setCellValue(dob);
    Datarow1.createCell(3).setCellValue(gender);
    
    Datarow1.createCell(4).setCellValue(chart);
    Datarow1.createCell(5).setCellValue(insurance);
    Datarow1.createCell(6).setCellValue(provider);
    System.out.println("-------------------------------------------------------------------------"); 
    // Write changes back to file





    writeExcelDatas1();


}

    public static void writeExcelDatas() throws IOException {
        // Load properties file
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
        }

        // Get the file path from properties
        String filePath = prop.getProperty("excel.file.path1");

        if (filePath == null) {
            throw new IllegalArgumentException("Excel file path is not set in config.properties");
        }

        // Write workbook data to the file
        try (FileOutputStream excelfile = new FileOutputStream(filePath)) {
            workbook.write(excelfile);
        }
       
    }
    
    
    public static void writeExcelDatas1() throws IOException {
        // Load properties file
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
        }

        // Get the file path from properties
        String filePath = prop.getProperty("excel.file.path2");

        if (filePath == null) {
            throw new IllegalArgumentException("Excel file path is not set in config.properties");
        }

        // Write workbook data to the file
        try (FileOutputStream excelfile = new FileOutputStream(filePath)) {
            workbook1.write(excelfile);
        }
       
    }  
	
	public static void closeExcel() throws IOException {	
	
	      workbook.close();
	}


}
    
    
	
