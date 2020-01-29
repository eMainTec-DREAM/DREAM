package dream.consult.program.foreport.dto;

import common.bean.BaseDTO;

/**
 * 내정보 공통 DTO
 * @author  kim21017
 * @version $Id: MaReportCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaReportCommonDTO extends BaseDTO
{
	/** excel data path */
    private String filePath 			= "";
    /** values */
    private String[][] values 			= null;
    /** data size(table 생성시에 columns의 길이값) */
    private int[] dataSize 				= null;
    /** 임시로 파일명을 저장해 둠 */
    private String tempFileName 		= "";
    
    
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String[][] getValues() {
		return values;
	}
	public void setValues(String[][] values) {
		this.values = values;
	}
	public int[] getDataSize() {
		return dataSize;
	}
	public void setDataSize(int[] dataSize) {
		this.dataSize = dataSize;
	}
	public String getTempFileName() {
		return tempFileName;
	}
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}
}
