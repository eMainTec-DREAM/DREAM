package dream.doc.ctg.service;

import java.util.List;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgLovDTO;
import dream.doc.ctg.form.DocCtgLovForm;

/**
 * 문서분류 LOV- 목록 service
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
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(DocCtgLovDTO docCtgLovDTO, DocCtgLovForm docCtgLovForm, User user);

}
