package dream.part.list.service.spring;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import dream.part.list.dao.MaPtMstrStockListDAO;
import dream.part.list.dto.MaPtMstrStockListDTO;
import dream.part.list.form.MaPtMstrStockListForm;
import dream.part.list.service.MaPtMstrStockListService;
import dream.part.stk.dao.MaPtStckDetailDAO;
import dream.part.stk.dto.MaPtStckDetailDTO;

/**
 * 자재재고 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrStockListServiceTarget"
 * @spring.txbn id="maPtMstrStockListService"
 * @spring.property name="maPtMstrStockListDAO" ref="maPtMstrStockListDAO"
 */
public class MaPtMstrStockListServiceImpl implements MaPtMstrStockListService
{
    private MaPtMstrStockListDAO maPtMstrStockListDAO = null;
    
    public MaPtMstrStockListDAO getMaPtMstrStockListDAO() 
    {
		return maPtMstrStockListDAO;
	}

	public void setMaPtMstrStockListDAO(MaPtMstrStockListDAO maPtMstrStockListDAO) 
	{
		this.maPtMstrStockListDAO = maPtMstrStockListDAO;
	}
	
    public List findPtMstrStockList(MaPtMstrStockListDTO maPtMstrStockListDTO, User user)
    {      
        return maPtMstrStockListDAO.findPtMstrStockList(maPtMstrStockListDTO,user);
    }
	
    @Override
    public String findTotalCount(MaPtMstrStockListDTO maPtMstrStockListDTO, User user) throws Exception
    {
        return maPtMstrStockListDAO.findTotalCount(maPtMstrStockListDTO, user);
    }
}

