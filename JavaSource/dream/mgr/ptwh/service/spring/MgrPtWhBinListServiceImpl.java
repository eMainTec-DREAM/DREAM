package dream.mgr.ptwh.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dao.MgrPtWhBinListDAO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
import dream.mgr.ptwh.service.MgrPtWhBinListService;

/**
 * 부품창고 보관위치 - List Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrPtWhBinListServiceTarget"
 * @spring.txbn id="mgrPtWhBinListService"
 * @spring.property name="mgrPtWhBinListDAO" ref="mgrPtWhBinListDAO"
 */
public class MgrPtWhBinListServiceImpl implements MgrPtWhBinListService
{
	private MgrPtWhBinListDAO mgrPtWhBinListDAO = null;

	public List findPtWhEmpList(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception
    {      
        return mgrPtWhBinListDAO.findPtWhEmpList(mgrPtWhBinListDTO,user);
    }

	public int deletePtWhEmpList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrPtWhBinListDAO.deletePtWhEmpList(id, user);
            }
        return result;
    }

	public MgrPtWhBinListDAO getMgrPtWhBinListDAO() {
		return mgrPtWhBinListDAO;
	}

	public void setMgrPtWhBinListDAO(MgrPtWhBinListDAO mgrPtWhBinListDAO) {
		this.mgrPtWhBinListDAO = mgrPtWhBinListDAO;
	}
	public String findTotalCount(MgrPtWhBinListDTO mgrPtWhBinListDTO,User user) throws Exception
    {
        return mgrPtWhBinListDAO.findTotalCount(mgrPtWhBinListDTO, user);
    }

    @Override
    public int insertQrList(String[] selectRows, String fileName, User user) throws Exception
    {
        //일단 지운뒤에 insert하여 이전의  출력 데이터를 삭제한다.
        mgrPtWhBinListDAO.deleteQrList(user, fileName);
        
        int result = 0;
        if(!selectRows.equals(null))
            for(String id : selectRows)
            {
                result = result + mgrPtWhBinListDAO.insertQrList(id, fileName, user);
            }
        return result;
    }
}

