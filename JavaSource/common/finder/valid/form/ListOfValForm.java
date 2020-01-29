package common.finder.valid.form;

import common.finder.valid.dto.ListOfValDTO;
import common.struts.BaseForm;
import dream.comm.form.MaFinderAcForm;

/**
 * List Of Value Form
 * @author  javaworker
 * @version $Id: ListOfValForm.java,v 1.1 2013/08/30 09:12:46 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="listOfValForm"
 */
public class ListOfValForm extends MaFinderAcForm
{
    /** List Of Val DTO */
    private ListOfValDTO listOfValDTO = new ListOfValDTO();

    public ListOfValDTO getListOfValDTO()
    {
        return listOfValDTO;
    }

    public void setListOfValDTO(ListOfValDTO listOfValDTO)
    {
        this.listOfValDTO = listOfValDTO;
    }
}
