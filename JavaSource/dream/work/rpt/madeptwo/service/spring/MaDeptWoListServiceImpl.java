package dream.work.rpt.madeptwo.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.rpt.madeptwo.dao.MaDeptWoListDAO;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;
import dream.work.rpt.madeptwo.service.MaDeptWoListService;

/**
 * 부서별작업분석 serviceimpl
 * @author kim21017
 * @version $Id: MaDeptWoListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maDeptWoListServiceTarget"
 * @spring.txbn id="maDeptWoListService"
 * @spring.property name="maDeptWoListDAO" ref="maDeptWoListDAO"
 */
public class MaDeptWoListServiceImpl implements MaDeptWoListService
{
    private MaDeptWoListDAO maDeptWoListDAO = null;

    public MaDeptWoListDAO getMaDeptWoListDAO() {
		return maDeptWoListDAO;
	}

	public void setMaDeptWoListDAO(MaDeptWoListDAO maDeptWoListDAO) {
		this.maDeptWoListDAO = maDeptWoListDAO;
	}
	public List findCntChart(MaDeptWoListDTO maDeptWoListDTO)
    {      
        return maDeptWoListDAO.findCntChart(maDeptWoListDTO);
    }
	public List findTimeChart(MaDeptWoListDTO maDeptWoListDTO)
    {      
        return maDeptWoListDAO.findTimeChart(maDeptWoListDTO);
    }
	public List findDeptWoList(MaDeptWoListDTO maDeptWoListDTO, User user)
    {      
		/*return maDeptWoListDAO.findDeptWoList(maDeptWoListDTO,user);*/
		List list = maDeptWoListDAO.findDeptWoList(maDeptWoListDTO,user);

		return CommonUtil.makeTreeList(list, "PDEPTID", "DEPTID", false);
    }
}