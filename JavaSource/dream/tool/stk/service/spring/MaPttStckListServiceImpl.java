package dream.tool.stk.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.tool.stk.dao.MaPttStckDetailDAO;
import dream.tool.stk.dao.MaPttStckListDAO;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;
import dream.tool.stk.form.MaPttStckListForm;
import dream.tool.stk.service.MaPttStckListService;

/**
 * ������� - ��� serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPttStckListServiceTarget"
 * @spring.txbn id="maPttStckListService"
 * @spring.property name="maPttStckListDAO" ref="maPttStckListDAO"
 * @spring.property name="maPttStckDetailDAO" ref="maPttStckDetailDAO"
 */
public class MaPttStckListServiceImpl implements MaPttStckListService
{
    private MaPttStckListDAO maPttStckListDAO = null;
    private MaPttStckDetailDAO maPttStckDetailDAO = null;

    public MaPttStckListDAO getMaPttStckListDAO() 
    {
		return maPttStckListDAO;
	}

	public void setMaPttStckListDAO(MaPttStckListDAO maPttStckListDAO) 
	{
		this.maPttStckListDAO = maPttStckListDAO;
	}
	
	public MaPttStckDetailDAO getMaPttStckDetailDAO()
    {
        return maPttStckDetailDAO;
    }

    public void setMaPttStckDetailDAO(MaPttStckDetailDAO maPttStckDetailDAO)
    {
        this.maPttStckDetailDAO = maPttStckDetailDAO;
    }

    public List findPtStckList(MaPttStckCommonDTO maPttStckCommonDTO)
    {      
        return maPttStckListDAO.findPtStckList(maPttStckCommonDTO);
    }
	
	public int deletePtStck(String compNo, String[] deleteRows, String[] deleteRowsExt, User loginUser) throws Exception
	{
        int result = 0;

        MaPttStckDetailDTO maPttStckDetailDTO = null;
        int stockQty = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            // ������ �������� 0���� ���߱� ����, ���� �������� ��ȸ�Ѵ�. 
            maPttStckDetailDTO = new MaPttStckDetailDTO();
            maPttStckDetailDTO.setCompNo(compNo);
            maPttStckDetailDTO.setWcodeId(deleteRows[i]);
            maPttStckDetailDTO.setPartId(deleteRowsExt[i]);
            
            stockQty= CommonUtil.parseInt(maPttStckDetailDAO.getAllStockQty(maPttStckDetailDTO));
            //��� ���� ���ؿ���
            String[] gradeArr = maPttStckDetailDAO.getStockGrade(maPttStckDetailDTO);
            // �������� �����ϸ� ��� ó�� 
            if(stockQty > 0)
            {
            	for (int j = 0; j < gradeArr.length; j++) {
	                String ptIssHistId = maPttStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	                maPttStckDetailDAO.insertPtIssHistory(ptIssHistId, stockQty+"",gradeArr[j], maPttStckDetailDTO, loginUser);
	                maPttStckDetailDAO.executeSpPtOutStock(compNo, ptIssHistId);
            	}
            }
            // �������� (-) �̸�, �������� ���̸�ŭ �԰��Ų��. ��, �������� 0�̵ǵ��� �԰��Ű�°�.  
            else if(stockQty < 0)
            {
            	for (int j = 0; j < gradeArr.length; j++) {
            		String ptRecHistId = maPttStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
                    maPttStckDetailDAO.insertPtRecHistory(ptRecHistId, (stockQty*-1)+"",gradeArr[j], maPttStckDetailDTO, loginUser);
                    maPttStckDetailDAO.executeSpPtInStock(compNo, ptRecHistId);
            	}
            }
            
            // ����ó���Ѵ�. 
            result = result + maPttStckListDAO.deletePtStck(compNo, deleteRows[i], deleteRowsExt[i]);
        }
        
        return result;
    }
	
	public int reqPtStck(MaPttStckListForm maPttStckListForm, User loginUser) throws Exception
	{
		
		String[] deleteRows = maPttStckListForm.getDeleteRows();  
		String[] wcodeId = maPttStckListForm.getDeleteRowsExt();
		//String[] partId = maPttStckListForm.getDeleteRowsExt1();

        int result = 0;
        result = result + maPttStckListDAO.reqHdrPtStck(maPttStckListForm,wcodeId[0],deleteRows[0],loginUser);
        for(int i = 0; deleteRows.length > i ; i++)
        {
            // ���Ž�û
            result = result + maPttStckListDAO.reqDtlPtStck(maPttStckListForm,deleteRows[i],loginUser);
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPttStckCommonDTO maPttStckCommonDTO, User user) {
		return maPttStckListDAO.findTotalCount(maPttStckCommonDTO, user);
	}
}

