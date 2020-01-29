package dream.work.rpt.mapmtrend.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dao.MaPmTrendChartDAO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendChartDTO;
import dream.work.rpt.mapmtrend.service.MaPmTrendChartService;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;

/**
 * 예방점검경향분석 serviceimpl
 * @author kim21017
 * @version $Id: MaPmTrendChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmTrendChartServiceTarget"
 * @spring.txbn id="maPmTrendChartService"
 * @spring.property name="maPmTrendChartDAO" ref="maPmTrendChartDAO"
 */
public class MaPmTrendChartServiceImpl implements MaPmTrendChartService
{
    private MaPmTrendChartDAO maPmTrendChartDAO = null;

    public MaPmTrendChartDAO getMaPmTrendChartDAO() {
		return maPmTrendChartDAO;
	}

	public void setMaPmTrendChartDAO(MaPmTrendChartDAO maPmTrendChartDAO) {
		this.maPmTrendChartDAO = maPmTrendChartDAO;
	}

	public List findPmList(MaPmTrendChartDTO maPmTrendChartDTO,User user)
    {      
        return maPmTrendChartDAO.findPmList(maPmTrendChartDTO,user);
    }
	
	public List findPmChart(MaPmTrendChartDTO maPmTrendChartDTO)
    {      
        return maPmTrendChartDAO.findPmChart(maPmTrendChartDTO);
    }
	
	public int createWo(MaPmTrendChartDTO maPmTrendChartDTO, User loginUser)
	{
	    return maPmTrendChartDAO.createWo(maPmTrendChartDTO, loginUser);
	}
	
	public String findTotalCount(MaPmTrendChartDTO maPmTrendChartDTO, User user)
    {
        return maPmTrendChartDAO.findTotalCount(maPmTrendChartDTO, user);
    }
}