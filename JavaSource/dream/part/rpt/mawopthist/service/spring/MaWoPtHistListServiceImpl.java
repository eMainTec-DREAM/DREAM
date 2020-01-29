package dream.part.rpt.mawopthist.service.spring;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import dream.part.rpt.mawopthist.dao.MaWoPtHistListDAO;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;
import dream.part.rpt.mawopthist.dto.MaWoPtHistListDTO;
import dream.part.rpt.mawopthist.form.MaWoPtHistListForm;
import dream.part.rpt.mawopthist.service.MaWoPtHistListService;

/**
 * ��ǰ����̷� - ��� serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maWoPtHistListServiceTarget"
 * @spring.txbn id="maWoPtHistListService"
 * @spring.property name="maWoPtHistListDAO" ref="maWoPtHistListDAO"
 */
public class MaWoPtHistListServiceImpl implements MaWoPtHistListService
{
    private MaWoPtHistListDAO maWoPtHistListDAO = null;

    public MaWoPtHistListDAO getMaWoPtHistListDAO() 
    {
		return maWoPtHistListDAO;
	}

	public void setMaWoPtHistListDAO(MaWoPtHistListDAO maWoPtHistListDAO) 
	{
		this.maWoPtHistListDAO = maWoPtHistListDAO;
	}

	public List findList(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User loginUser)
    {      
        return maWoPtHistListDAO.findList(maWoPtHistCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
        {
            MaWoPtHistListDTO maWoPtHistListDTO = null;
            for(String id : deleteRows)
            {
                maWoPtHistListDTO = new MaWoPtHistListDTO();
                maWoPtHistListDTO.setPartId(id);
                // ÷������ ���纰 �ŷ�ó ������ ���� ���� 
                result = result + maWoPtHistListDAO.deleteObjDoc(maWoPtHistListDTO, loginUser);
                result = result + maWoPtHistListDAO.deletePtVendor(maWoPtHistListDTO, loginUser);
                result = result + maWoPtHistListDAO.deleteParts(maWoPtHistListDTO, loginUser);
            }
        }
        return result;
    }
	public int reqPtBuy(MaWoPtHistListForm maWoPtHistListForm, User loginUser) throws Exception
	{
		String[] selectRows = maWoPtHistListForm.getSelectRows();
		selectRows = CommonUtil.makeRepRemoval(selectRows);
        int result = 0;
        result = result + maWoPtHistListDAO.reqHdrPtBuy(maWoPtHistListForm,selectRows[0],loginUser);
        String partGrade = "";
        
        if("N".equals(MwareConfig.getIsUsePartGrade())){
        	partGrade = MwareConfig.getPartGrade(); //��ǰ����� ������� �ʴ� ���� ������ �ִ� ��ǰ����� �����.
		}else{
			partGrade = "A"; //��ǰ����� ����� ���� ������ ��� ������ A�����.
		}
        
        for(int i = 0; selectRows.length > i ; i++)
        {
        	if(!"".equals(selectRows[i])&&!"null".equals(selectRows[i])){
//              // ���Ž�û
                result = result + maWoPtHistListDAO.reqDtlPtBuy(maWoPtHistListForm,selectRows[i],loginUser, partGrade);
        	}
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaWoPtHistCommonDTO maWoPtHistCommonDTO, User loginUser)
	{
		return maWoPtHistListDAO.findTotalCount(maWoPtHistCommonDTO, loginUser);
	}
}

