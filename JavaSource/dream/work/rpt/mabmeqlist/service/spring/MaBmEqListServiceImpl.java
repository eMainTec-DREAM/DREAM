package dream.work.rpt.mabmeqlist.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabmeqlist.dao.MaBmEqListDAO;
import dream.work.rpt.mabmeqlist.dto.MaBmEqListDTO;
import dream.work.rpt.mabmeqlist.service.MaBmEqListService;

/**
 * 설비별고장분석 serviceimpl
 * @author kim21017
 * @version $Id: MaBmEqListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maBmEqListServiceTarget"
 * @spring.txbn id="maBmEqListService"
 * @spring.property name="maBmEqListDAO" ref="maBmEqListDAO"
 */
public class MaBmEqListServiceImpl implements MaBmEqListService
{
    private MaBmEqListDAO maBmEqListDAO = null;

    public MaBmEqListDAO getMaBmEqListDAO() {
		return maBmEqListDAO;
	}

	public void setMaBmEqListDAO(MaBmEqListDAO maBmEqListDAO) {
		this.maBmEqListDAO = maBmEqListDAO;
	}
	
	public List findBmEqList(MaBmEqListDTO maBmEqListDTO, User user)
    {      
        return maBmEqListDAO.findBmEqList(maBmEqListDTO, user);
    }
}