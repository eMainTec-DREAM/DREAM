package dream.consult.comp.cdsys.service.spring;

import java.util.List;

import dream.consult.comp.cdsys.dao.MaCdSysListDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.service.MaCdSysListService;

/**
 * �ý����ڵ� - ��� serviceimpl
 * @author kim21017
 * @version $Id: MaCdSysListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maCdSysListServiceTarget"
 * @spring.txbn id="maCdSysListService"
 * @spring.property name="maCdSysListDAO" ref="maCdSysListDAO"
 */
public class MaCdSysListServiceImpl implements MaCdSysListService
{
    private MaCdSysListDAO maCdSysListDAO = null;

    public MaCdSysListDAO getMaCdSysListDAO() {
		return maCdSysListDAO;
	}

	public void setMaCdSysListDAO(MaCdSysListDAO maCdSysListDAO) {
		this.maCdSysListDAO = maCdSysListDAO;
	}

	public List findListTypeList(MaCdSysCommonDTO maCdSysCommonDTO)
    {      
        return maCdSysListDAO.findListTypeList(maCdSysCommonDTO);
    }
	
	public int deleteListType(String[] deleteRows) throws Exception{
        int result = 0;
//        MaCdSysDTOList maCdSysDTOList = null;
//        // sheet�� row ����ŭ �ݺ��Ѵ�. 
//        for (Iterator it = maCdSysList.iterator(); it.hasNext();)
//        {
//        	maCdSysDTOList = (MaCdSysDTOList) it.next();
//            // �ش� row�� ����(�Է�, ����, ����)�� �Ǵ��Ͽ� ���� ��ȸ�Ѵ�.
//            switch (maCdSysDTOList.getDtStatus())
//            {
//                // ����
//                case 'U':
//                	if("Y".equals(maCdSysDTOList.getIsDelCheck())){
//                		result = maCdSysListDAO.deleteListType(maCdSysDTOList);
//                	}
//                    break;
//                default:
//                    break;
//            }
//        }
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maCdSysListDAO.deleteListType(id);
            }
        
        return result;
    }
}

