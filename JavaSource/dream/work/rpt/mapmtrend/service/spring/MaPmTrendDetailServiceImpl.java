package dream.work.rpt.mapmtrend.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dao.MaPmTrendDetailDAO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;
import dream.work.rpt.mapmtrend.service.MaPmTrendDetailService;

/**
 * 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPmTrendDetailServiceTarget"
 * @spring.txbn id="maPmTrendDetailService"
 * @spring.property name="maPmTrendDetailDAO" ref="maPmTrendDetailDAO"
 */
public class MaPmTrendDetailServiceImpl implements MaPmTrendDetailService
{
    private MaPmTrendDetailDAO maPmTrendDetailDAO = null;
    
    public MaPmTrendDetailDAO getMaPmTrendDetailDAO()
    {
        return maPmTrendDetailDAO;
    }
    
    public void setMaPmTrendDetailDAO(
            MaPmTrendDetailDAO maPmTrendDetailDAO)
    {
        this.maPmTrendDetailDAO = maPmTrendDetailDAO;
    }
    
    public List findDetail(MaPmTrendCommonDTO maPmTrendCommonDTO, MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser)
    {
        return maPmTrendDetailDAO.findDetail(maPmTrendCommonDTO, maPmTrendDetailDTO, loginUser);
        
    }
    public int createWo(MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser)
    {
        return maPmTrendDetailDAO.createWo(maPmTrendDetailDTO, loginUser);
    }
}

