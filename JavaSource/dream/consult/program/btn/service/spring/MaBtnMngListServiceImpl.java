package dream.consult.program.btn.service.spring;

import java.util.List;

import dream.consult.program.btn.dao.MaBtnMngListDAO;
import dream.consult.program.btn.dto.MaBtnMngCommonDTO;
import dream.consult.program.btn.service.MaBtnMngListService;

/**
 * 버튼 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaBtnMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maBtnMngListServiceTarget"
 * @spring.txbn id="maBtnMngListService"
 * @spring.property name="maBtnMngListDAO" ref="maBtnMngListDAO"
 */
public class MaBtnMngListServiceImpl implements MaBtnMngListService
{
    private MaBtnMngListDAO maBtnMngListDAO = null;

    public MaBtnMngListDAO getMaBtnMngListDAO() {
		return maBtnMngListDAO;
	}

	public void setMaBtnMngListDAO(MaBtnMngListDAO maBtnMngListDAO) {
		this.maBtnMngListDAO = maBtnMngListDAO;
	}

	public List findBtnList(MaBtnMngCommonDTO maBtnMngCommonDTO)
    {      
        return maBtnMngListDAO.findBtnList(maBtnMngCommonDTO);
    }
	
	public int deleteBtn(String[] deleteRows) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maBtnMngListDAO.deleteBtn(id);
            }
        return result;
    }
}

