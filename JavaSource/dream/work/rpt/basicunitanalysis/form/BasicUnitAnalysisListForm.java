package dream.work.rpt.basicunitanalysis.form;

import common.struts.BaseForm;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisCommonDTO;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisDetailDTO;

/**
 * 원단위분석
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="basicUnitAnalysisListForm"
 */
public class BasicUnitAnalysisListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO = new BasicUnitAnalysisCommonDTO();
    
    private BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO = new BasicUnitAnalysisDetailDTO();
    
    public BasicUnitAnalysisCommonDTO getBasicUnitAnalysisCommonDTO()
    {
        return basicUnitAnalysisCommonDTO;
    }

    public void setBasicUnitAnalysisCommonDTO(
            BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO)
    {
        this.basicUnitAnalysisCommonDTO = basicUnitAnalysisCommonDTO;
    }
    
    public BasicUnitAnalysisDetailDTO getBasicUnitAnalysisDetailDTO()
    {
        return basicUnitAnalysisDetailDTO;
    }

    public void setBasicUnitAnalysisDetailDTO(
            BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO)
    {
        this.basicUnitAnalysisDetailDTO = basicUnitAnalysisDetailDTO;
    }
	
}
