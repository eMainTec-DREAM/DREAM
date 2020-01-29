package dream.part.rpt.mastat.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mastat.dao.MaPtRepStatListDAO;
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;
import dream.part.rpt.mastat.service.MaPtRepStatListService;

/**
 * 자재수리내역 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtRepStatListServiceTarget"
 * @spring.txbn id="maPtRepStatListService"
 * @spring.property name="maPtRepStatListDAO" ref="maPtRepStatListDAO"
 */
public class MaPtRepStatListServiceImpl implements MaPtRepStatListService
{
    private MaPtRepStatListDAO maPtRepStatListDAO = null;

    public MaPtRepStatListDAO getMaPtRepStatListDAO() 
    {
        return maPtRepStatListDAO;
    }

    public void setMaPtRepStatListDAO(MaPtRepStatListDAO maPtRepStatListDAO) 
    {
        this.maPtRepStatListDAO = maPtRepStatListDAO;
    }

    public List findList(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user)
    {      
        return maPtRepStatListDAO.findList(maPtRepStatCommonDTO, user);
    }

	@Override
	public String findTotalCount(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user)
	{
		return maPtRepStatListDAO.findTotalCount(maPtRepStatCommonDTO, user);
	}
    

}