package dream.mapt.mastat.service.spring;

import java.util.List;

import dream.mapt.mastat.dao.MaPtExpStatListDAO;
import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;
import dream.mapt.mastat.service.MaPtExpStatListService;

/**
 * 자재비용분석 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtExpStatListServiceTarget"
 * @spring.txbn id="maPtExpStatListService"
 * @spring.property name="maPtExpStatListDAO" ref="maPtExpStatListDAO"
 */
public class MaPtExpStatListServiceImpl implements MaPtExpStatListService
{
    private MaPtExpStatListDAO maPtExpStatListDAO = null;

    public MaPtExpStatListDAO getMaPtExpStatListDAO() 
    {
        return maPtExpStatListDAO;
    }

    public void setMaPtExpStatListDAO(MaPtExpStatListDAO maPtExpStatListDAO) 
    {
        this.maPtExpStatListDAO = maPtExpStatListDAO;
    }

    public List findList(MaPtExpStatCommonDTO maPtExpStatCommonDTO)
    {      
        return maPtExpStatListDAO.findList(maPtExpStatCommonDTO);
    }
    

}