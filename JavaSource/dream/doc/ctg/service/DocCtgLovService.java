package dream.doc.ctg.service;

import java.util.List;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgLovDTO;
import dream.doc.ctg.form.DocCtgLovForm;

/**
 * �����з� LOV- ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface DocCtgLovService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param docCtgCommonDTO 
     * @param docCtgLovForm
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(DocCtgLovDTO docCtgLovDTO, DocCtgLovForm docCtgLovForm, User user);

}
