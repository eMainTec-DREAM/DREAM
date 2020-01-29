package dream.consult.program.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.lang.dao.MaLangMngListDAO;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;
import dream.consult.program.lang.service.MaLangMngListService;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 다국어 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaLangMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maLangMngListServiceTarget"
 * @spring.txbn id="maLangMngListService"
 * @spring.property name="maLangMngListDAO" ref="maLangMngListDAO"
 */
public class MaLangMngListServiceImpl implements MaLangMngListService
{
    private MaLangMngListDAO maLangMngListDAO = null;

    public MaLangMngListDAO getMaLangMngListDAO() {
		return maLangMngListDAO;
	}

	public void setMaLangMngListDAO(MaLangMngListDAO maLangMngListDAO) {
		this.maLangMngListDAO = maLangMngListDAO;
	}

	public List findKeyList(MaLangMngCommonDTO maLangMngCommonDTO, User user)
    {      
        return maLangMngListDAO.findKeyList(maLangMngCommonDTO, user);
    }
	
	
	public int deleteKey(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null)){
        	for(String id : deleteRows)
            {
                result = result + maLangMngListDAO.deleteKey(id);
            }
        }
        
        return result;
    }
	
	public String findTotalCount(MaLangMngCommonDTO maLangMngCommonDTO,User user) throws Exception
    {
        return maLangMngListDAO.findTotalCount(maLangMngCommonDTO, user);
    }
}

