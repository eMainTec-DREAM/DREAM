package dream.consult.comp.cdsys.service.spring;

import java.util.List;

import dream.consult.comp.cdsys.dao.MaCdSysCodeListDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.service.MaCdSysCodeListService;

/**
 * �ý����ڵ� detail-code ��� serviceimpl
 * @author kim21017
 * @version $Id: MaCdSysCodeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maCdSysCodeListServiceTarget"
 * @spring.txbn id="maCdSysCodeListService"
 * @spring.property name="maCdSysCodeListDAO" ref="maCdSysCodeListDAO"
 */
public class MaCdSysCodeListServiceImpl implements MaCdSysCodeListService
{
    private MaCdSysCodeListDAO maCdSysCodeListDAO = null;


	public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO)
    {      
        return maCdSysCodeListDAO.findCodeList(maCdSysCommonDTO, maCdSysCodeListDTO);
    }

	public MaCdSysCodeListDAO getMaCdSysCodeListDAO() {
		return maCdSysCodeListDAO;
	}


	public void setMaCdSysCodeListDAO(MaCdSysCodeListDAO maCdSysCodeListDAO) {
		this.maCdSysCodeListDAO = maCdSysCodeListDAO;
	}
	
	public int deleteCodeList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
//        ListTypeCodeDTOList listTypeCodeDTOList = null;
//        // sheet�� row ����ŭ �ݺ��Ѵ�. 
//        for (Iterator it = listTypeCodeList.iterator(); it.hasNext();)
//        {
//        	listTypeCodeDTOList = (ListTypeCodeDTOList) it.next();
//            // �ش� row�� ����(�Է�, ����, ����)�� �Ǵ��Ͽ� ���� ��ȸ�Ѵ�.
//            switch (listTypeCodeDTOList.getDtStatus())
//            {
//                // ����
//                case 'U':
//                	if("Y".equals(listTypeCodeDTOList.getIsDelCheck())){
//                		result = maCdSysCodeListDAO.deleteCodeList(listTypeCodeDTOList);
//                	}
//                    break;
//                default:
//                    break;
//            }
//        }
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maCdSysCodeListDAO.deleteCodeList(deleteRows[i], deleteRowsExt[i] );
        }
        
        return result;
    }
}

