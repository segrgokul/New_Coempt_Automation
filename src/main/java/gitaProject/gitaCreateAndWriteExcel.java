package gitaProject;

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

public class GitaCreateAndWriteExcel {

	 static XSSFWorkbook workbook = new XSSFWorkbook();

     // Get the sheet by name
     static XSSFSheet sheet = workbook.createSheet();
	

    public static void writeExcelData(int rowIndex,String studentRegno,String studentSemester,String serialNo,String subjectNames,String subjectCode,String credit,String type,String st,String qt,String as,String mte,String emptyColumn1,String emptyColumn2,String emptyColumn3,String to40,String endSemExam,
    	String	to100,String tm,String grade,String totalCredits,String sgpaMarks,String cgpaMarks,String totalTO100,String totalTM) throws IOException {
  
    	                // Create a header row if the file is newly created
    	                Row datasRow = sheet.createRow(0);
    	      
//    	                since the serialNo is not need i just this below one to match extact no of columns
    	                
//    	                String[] datavalues = { serialNo,studentRegno,studentSemester, subjectNames,subjectCode,credit,type,st,qt,as,mte,emptyColumn1,emptyColumn2,emptyColumn3,to40,endSemExam
//    	                		,to100,totalMarks,grade};
          
    	                String[] datavalues = {studentRegno,studentSemester, subjectNames,subjectCode,credit,type,st,qt,as,mte,emptyColumn1,emptyColumn2,emptyColumn3,to40,endSemExam
    	                		,to100,tm,grade,totalCredits,sgpaMarks,cgpaMarks,totalTO100,totalTM};

    	                
    	                for (int i = 0; i < datavalues.length; i++) {
    	                	datasRow.createCell(i).setCellValue(datavalues[i]);
    	                }
    	            
    	                XSSFRow firstrow = sheet.getRow(0);
    	        	    if (firstrow == null) {
    	        	        firstrow = sheet.createRow(0);
    	        	    }
    	  
//    	        	  since seral no is no need i just commented it  
		//				firstrow.createCell(0).setCellValue("serialNo");	
    	        	   firstrow.createCell(0).setCellValue("studentRegno");
    	        	   
    	        	   firstrow.createCell(1).setCellValue("Semesters");
    	        	   
    	        	    firstrow.createCell(2).setCellValue("subjectNames");
    	          
    	        		firstrow.createCell(3).setCellValue("subjectCode");
    	        	//	firstrow.createCell(5).setCellValue("Dummydata"); 
    	        		
    	        		firstrow.createCell(4).setCellValue("credit");
    	        		 
    	        		 firstrow.createCell(5).setCellValue("type");
    	        		 firstrow.createCell(6).setCellValue("ST(5)");
    	        		  firstrow.createCell(7).setCellValue("QT(5)");
    	        		  firstrow.createCell(8).setCellValue("AS(5)");
    	        		  
    	        		  firstrow.createCell(9).setCellValue("MTE(25)");
    	        		  
    	        		  firstrow.createCell(10).setCellValue("emptyColumn1");
    	        		  firstrow.createCell(11).setCellValue("emptyColumn2");
    	        		  firstrow.createCell(12).setCellValue("emptyColumn3");
    	        		  firstrow.createCell(13).setCellValue("To(40)");
    	        		  firstrow.createCell(14).setCellValue("EndSemExam(60)");
    	        		  firstrow.createCell(15).setCellValue("TO(100)");
    	        		  firstrow.createCell(16).setCellValue("TM");  
    	        		  firstrow.createCell(17).setCellValue("Grade");
    	        		  firstrow.createCell(18).setCellValue("Total Credits");  
    	        		  firstrow.createCell(19).setCellValue("SGPA");  
    	        		  firstrow.createCell(20).setCellValue("CGPA");  
    	        		  firstrow.createCell(21).setCellValue("Total TO(100)");  
    	        		  firstrow.createCell(22).setCellValue("Total TM");  
    	                
    	            // Create a new row based on the rowIndex from the loop
    	        		  Row Datarow = sheet.createRow(rowIndex); 

    	            // Write each value in the correct column
    	     //       Datarow.createCell(0).setCellValue(serialNo);
    	      //      Datarow.createCell(1).setCellValue(subjectNames);

    	        		  System.out.println("-------------------------------------------------------------------------");
    	        		     System.out.println("Written: " + subjectNames + " in row " + rowIndex);		  
	        		     
    	        //since sr.no is not need i commented it		     
    	        //     Datarow.createCell(0).setCellValue(serialNo);
    	            Datarow.createCell(0).setCellValue(studentRegno);
    	            
    	            Datarow.createCell(1).setCellValue(studentSemester);
    	            
    	        	Datarow.createCell(2).setCellValue(subjectNames);
    	            
    	            Datarow.createCell(3).setCellValue(subjectCode);
    	            
    	//            Datarow.createCell(5).setCellValue(extraSubjectCode);// skipped because of extra code

    	            Datarow.createCell(4).setCellValue(credit);
    	            Datarow.createCell(5).setCellValue(type);
    	            Datarow.createCell(6).setCellValue(st);
    	            Datarow.createCell(7).setCellValue(qt);
    	            Datarow.createCell(8).setCellValue(as);
    	            Datarow.createCell(9).setCellValue(mte);
    	            Datarow.createCell(10).setCellValue(emptyColumn1);
    	            Datarow.createCell(11).setCellValue(emptyColumn2);
    	            Datarow.createCell(12).setCellValue(emptyColumn3);
    	            Datarow.createCell(13).setCellValue(to40);
    	            Datarow.createCell(14).setCellValue(endSemExam);
    	            Datarow.createCell(15).setCellValue(to100);
    	            Datarow.createCell(16).setCellValue(tm);
    	            Datarow.createCell(17).setCellValue(grade);
    	            Datarow.createCell(18).setCellValue(totalCredits);
    	            Datarow.createCell(19).setCellValue(sgpaMarks);
    	            Datarow.createCell(20).setCellValue(cgpaMarks);
    	            Datarow.createCell(21).setCellValue(totalTO100);
    	            Datarow.createCell(22).setCellValue(totalTM);
        	        System.out.println("-------------------------------------------------------------------------"); 
    	            // Write changes back to file
    	        

    	       

  
           writeExcelDatas();

			 
    }
		

    public static void writeExcelDatas() throws IOException {
        // Load properties file
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
        }

        // Get the file path from properties
        String filePath = prop.getProperty("excel.file.path");

        if (filePath == null) {
            throw new IllegalArgumentException("Excel file path is not set in config.properties");
        }

        // Write workbook data to the file
        try (FileOutputStream excelfile = new FileOutputStream(filePath)) {
            workbook.write(excelfile);
        }
       
    }
	  
	
	public static void closeExcel() throws IOException {	
	
	      workbook.close();
	}


}
    
    
	
