package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;


/**
 * WorkListCinsPlanMstr Page - List Form
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workListCinsPlanMstrListForm"
 * */
public class WorkListCinsPlanMstrListForm extends BaseForm {
    
    private WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO = new WorkListCinsPlanMstrCommonDTO();

    public WorkListCinsPlanMstrCommonDTO getWorkListCinsPlanMstrCommonDTO() {
        return workListCinsPlanMstrCommonDTO;
    }

    public void setWorkListCinsPlanMstrCommonDTO(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO) {
        this.workListCinsPlanMstrCommonDTO = workListCinsPlanMstrCommonDTO;
    }
}