package dream.req.work.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.work.dao.MaWoReqTypeSelectDAO;
import dream.req.work.dto.MaWoReqTypeSelectDTO;
import dream.req.work.service.MaWoReqTypeSelectService;

/**
 * ÀÛ¾÷¿äÃ»À¯Çü ÆË¾÷ ServiceImpl
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="maWoReqTypeSelectServiceTarget"
 * @spring.txbn id="maWoReqTypeSelectService"
 * @spring.property name="maWoReqTypeSelectDAO" ref="maWoReqTypeSelectDAO"
 */
public class MaWoReqTypeSelectServiceImpl implements MaWoReqTypeSelectService
{
    /** ÆË¾÷ DAO */
    private MaWoReqTypeSelectDAO maWoReqTypeSelectDAO = null;
    
    public MaWoReqTypeSelectDAO getMaWoReqTypeSelectDAO() {
		return maWoReqTypeSelectDAO;
	}

	public void setMaWoReqTypeSelectDAO(MaWoReqTypeSelectDAO maWoReqTypeSelectDAO) {
		this.maWoReqTypeSelectDAO = maWoReqTypeSelectDAO;
	}

	public List findList(User loginUser, MaWoReqTypeSelectDTO maWoReqTypeSelectDTO)
    {
        List resultList = null;
        resultList = maWoReqTypeSelectDAO.findList(loginUser,maWoReqTypeSelectDTO);
        
        return resultList;
    }
}