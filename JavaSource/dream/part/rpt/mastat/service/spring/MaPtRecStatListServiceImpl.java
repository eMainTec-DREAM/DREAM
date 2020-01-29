package dream.part.rpt.mastat.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.mastat.dao.MaPtRecStatListDAO;
import dream.part.rpt.mastat.dto.MaPtRecStatCommonDTO;
import dream.part.rpt.mastat.service.MaPtRecStatListService;

/**
 * 자재입고내역 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtRecStatListServiceTarget"
 * @spring.txbn id="maPtRecStatListService"
 * @spring.property name="maPtRecStatListDAO" ref="maPtRecStatListDAO"
 */
public class MaPtRecStatListServiceImpl implements MaPtRecStatListService
{
    private MaPtRecStatListDAO maPtRecStatListDAO = null;

    public MaPtRecStatListDAO getMaPtRecStatListDAO() 
    {
        return maPtRecStatListDAO;
    }

    public void setMaPtRecStatListDAO(MaPtRecStatListDAO maPtRecStatListDAO) 
    {
        this.maPtRecStatListDAO = maPtRecStatListDAO;
    }

    public List findList(MaPtRecStatCommonDTO maPtRecStatCommonDTO)
    {      
        return maPtRecStatListDAO.findList(maPtRecStatCommonDTO);
    }

    @Override
    public String findTotalCount(MaPtRecStatCommonDTO maPtRecStatCommonDTO, User user)
    {
        return maPtRecStatListDAO.findTotalCount(maPtRecStatCommonDTO, user);
    }
    

}