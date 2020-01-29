package dream.work.rpt.mabmctgchart.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabmctgchart.dao.MaBmCtgChartDAO;
import dream.work.rpt.mabmctgchart.dto.MaBmCtgChartDTO;
import dream.work.rpt.mabmctgchart.service.MaBmCtgChartService;

/**
 * 설비종류별고장분석 serviceimpl
 * @author kim21017
 * @version $Id: MaBmCtgChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maBmCtgChartServiceTarget"
 * @spring.txbn id="maBmCtgChartService"
 * @spring.property name="maBmCtgChartDAO" ref="maBmCtgChartDAO"
 */
public class MaBmCtgChartServiceImpl implements MaBmCtgChartService
{
    private MaBmCtgChartDAO maBmCtgChartDAO = null;

    public MaBmCtgChartDAO getMaBmCtgChartDAO() {
		return maBmCtgChartDAO;
	}

	public void setMaBmCtgChartDAO(MaBmCtgChartDAO maBmCtgChartDAO) {
		this.maBmCtgChartDAO = maBmCtgChartDAO;
	}

	public List findBmList(MaBmCtgChartDTO maBmCtgChartDTO, User user)
    {      
        return maBmCtgChartDAO.findBmList(maBmCtgChartDTO, user);
    }
	
	public List findBmChart(MaBmCtgChartDTO maBmCtgChartDTO, User user)
    {      
        return maBmCtgChartDAO.findBmChart(maBmCtgChartDTO, user);
    }
}