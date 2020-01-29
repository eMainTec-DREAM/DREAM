package dream.part.stk.service.spring;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import dream.part.stk.dao.MaPtStckDetailDAO;
import dream.part.stk.dao.MaPtStckListDAO;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.part.stk.form.MaPtStckListForm;
import dream.part.stk.service.MaPtStckListService;

/**
 * ������� - ��� serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtStckListServiceTarget"
 * @spring.txbn id="maPtStckListService"
 * @spring.property name="maPtStckListDAO" ref="maPtStckListDAO"
 * @spring.property name="maPtStckDetailDAO" ref="maPtStckDetailDAO"
 */
public class MaPtStckListServiceImpl implements MaPtStckListService
{
    private MaPtStckListDAO maPtStckListDAO = null;
    private MaPtStckDetailDAO maPtStckDetailDAO = null;

    public MaPtStckListDAO getMaPtStckListDAO() 
    {
		return maPtStckListDAO;
	}

	public void setMaPtStckListDAO(MaPtStckListDAO maPtStckListDAO) 
	{
		this.maPtStckListDAO = maPtStckListDAO;
	}
	
	public MaPtStckDetailDAO getMaPtStckDetailDAO()
    {
        return maPtStckDetailDAO;
    }

    public void setMaPtStckDetailDAO(MaPtStckDetailDAO maPtStckDetailDAO)
    {
        this.maPtStckDetailDAO = maPtStckDetailDAO;
    }

    public List findPtStckList(MaPtStckCommonDTO maPtStckCommonDTO, User user)
    {      
        return maPtStckListDAO.findPtStckList(maPtStckCommonDTO,user);
    }
	
	public int deletePtStck(String compNo, String[] deleteRows, String[] deleteRowsExt, User loginUser) throws Exception
	{
        int result = 0;

        MaPtStckDetailDTO maPtStckDetailDTO = null;
        int stockQty = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            // ������ �������� 0���� ���߱� ����, ���� �������� ��ȸ�Ѵ�. 
            maPtStckDetailDTO = new MaPtStckDetailDTO();
            maPtStckDetailDTO.setCompNo(compNo);
            maPtStckDetailDTO.setWcodeId(deleteRows[i]);
            maPtStckDetailDTO.setPartId(deleteRowsExt[i]);
            
            stockQty= CommonUtil.parseInt(maPtStckDetailDAO.getAllStockQty(maPtStckDetailDTO));
            //��� ���� ���ؿ���
            String[] gradeArr = maPtStckDetailDAO.getStockGrade(maPtStckDetailDTO);
            // �������� �����ϸ� ��� ó�� 
            if(stockQty > 0)
            {
            	for (int j = 0; j < gradeArr.length; j++) {
	                String ptIssHistId = maPtStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	                maPtStckDetailDAO.insertPtIssHistory(ptIssHistId, stockQty+"",gradeArr[j], maPtStckDetailDTO, loginUser);
	                maPtStckDetailDAO.executeSpPtOutStock(compNo, ptIssHistId);
            	}
            }
            // �������� (-) �̸�, �������� ���̸�ŭ �԰��Ų��. ��, �������� 0�̵ǵ��� �԰��Ű�°�.  
            else if(stockQty < 0)
            {
            	for (int j = 0; j < gradeArr.length; j++) {
            		String ptRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
                    maPtStckDetailDAO.insertPtRecHistory(ptRecHistId, (stockQty*-1)+"",gradeArr[j], maPtStckDetailDTO, loginUser);
                    maPtStckDetailDAO.executeSpPtInStock(compNo, ptRecHistId);
            	}
            }
            
            // ����ó���Ѵ�. 
            result = result + maPtStckListDAO.deletePtStck(compNo, deleteRows[i], deleteRowsExt[i]);
        }
        
        return result;
    }
	public int reqPtStck(MaPtStckListForm maPtStckListForm, User loginUser) throws Exception
	{
		String[] partId = maPtStckListForm.getDeleteRows();
		String[] recQty = maPtStckListForm.getDeleteRowsExt();
		String partGrade = "";
        int result = 0;
        result = result + maPtStckListDAO.reqHdrPtStck(maPtStckListForm,partId[0],loginUser);
        
        if("N".equals(MwareConfig.getIsUsePartGrade())){
        	partGrade = MwareConfig.getPartGrade(); //��ǰ����� ������� �ʴ� ���� ������ �ִ� ��ǰ����� �����.
		}else{
			partGrade = "A"; //��ǰ����� ����� ���� ������ ��� ������ A�����.
		}
        
        for(int i = 0; partId.length > i ; i++)
        {
            // ���Ž�û
            if("0".equals(recQty[i]) || "".equals(recQty[i])) {
                recQty[i] = "1";
            }
            result = result + maPtStckListDAO.reqDtlPtStck(maPtStckListForm,partId[i],recQty[i],loginUser, partGrade);
        }
        
        return result;
    }
	
	public int insertQrCode(String[] selectRows,String[] wcodeId, String compNo, User loginUser) throws Exception{
		
		//�ϴ� ����ڿ� insert�Ͽ� ������  ��� �����͸� �����Ѵ�.
		maPtStckListDAO.deleteQrCode(loginUser);
	    
        int result = 0;
        if(selectRows != null) {
            for(int i=0; i<selectRows.length;i++)
            {
                result = result + maPtStckListDAO.insertQrCode(selectRows[i],wcodeId[i],compNo,loginUser);
            }
        }
        return result;
    }
	public int insertListQrCode(MaPtStckCommonDTO maPtStckCommonDTO, User loginUser) throws Exception{
		
		//�ϴ� ����ڿ� insert�Ͽ� ������  ��� �����͸� �����Ѵ�.
		maPtStckListDAO.deleteQrCode(loginUser);
		return maPtStckListDAO.insertListQrCode(maPtStckCommonDTO,loginUser);
	}

    @Override
    public String findTotalCount(MaPtStckCommonDTO maPtStckCommonDTO, User user) throws Exception
    {
        return maPtStckListDAO.findTotalCount(maPtStckCommonDTO, user);
    }
}

