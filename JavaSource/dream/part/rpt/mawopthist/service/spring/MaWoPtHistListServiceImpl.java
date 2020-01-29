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
 * 부품사용이력 - 목록 serviceimpl
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
                // 첨부파일 자재별 거래처 삭제후 자재 삭제 
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
        	partGrade = MwareConfig.getPartGrade(); //부품등급을 사용하지 않는 경우는 설정에 있는 부품등급을 사용함.
		}else{
			partGrade = "A"; //부품등급을 사용할 경우는 구매일 경우 무조건 A등급임.
		}
        
        for(int i = 0; selectRows.length > i ; i++)
        {
        	if(!"".equals(selectRows[i])&&!"null".equals(selectRows[i])){
//              // 구매신청
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

