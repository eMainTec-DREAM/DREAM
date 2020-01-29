package dream.consult.rpt.mause.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.rpt.mause.dao.MaUseChartDAO;
import dream.consult.rpt.mause.dto.MaUseChartDTO;
import dream.consult.rpt.mause.service.MaUseChartService;

/**
 * 접속현황 serviceimpl
 * @author kim21017
 * @version $Id: MaUseChartServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maUseChartServiceTarget"
 * @spring.txbn id="maUseChartService"
 * @spring.property name="maUseChartDAO" ref="maUseChartDAO"
 */
public class MaUseChartServiceImpl implements MaUseChartService
{
    private MaUseChartDAO maUseChartDAO = null;

    public MaUseChartDAO getMaUseChartDAO() {
		return maUseChartDAO;
	}

	public void setMaUseChartDAO(MaUseChartDAO maUseChartDAO) {
		this.maUseChartDAO = maUseChartDAO;
	}
	public List findUseList(MaUseChartDTO maUseChartDTO, User user)
    {
        return maUseChartDAO.findUseList(maUseChartDTO, user);
    }

    @Override
    public String findTotalCount(MaUseChartDTO maUseChartDTO, User user)
    {
        return maUseChartDAO.findTotalCount(maUseChartDTO, user);
    }
}