package dream.part.rec.service.spring;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.rec.dao.MaPtRecDetailDAO;
import dream.part.rec.dao.MaPtRecListDAO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecDetailDTO;
import dream.part.rec.service.MaPtRecDetailService;
import dream.part.rec.service.MaPtRecListService;

/**
 * 구매입고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtRecListServiceTarget"
 * @spring.txbn id="maPtRecListService"
 * @spring.property name="maPtRecListDAO" ref="maPtRecListDAO"
 * @spring.property name="maPtRecDetailDAO" ref="maPtRecDetailDAO"
 * @spring.property name="maPtRecDetailService" ref="maPtRecDetailService"
 */
public class MaPtRecListServiceImpl implements MaPtRecListService
{
    private MaPtRecListDAO maPtRecListDAO = null;
    private MaPtRecDetailDAO maPtRecDetailDAO = null;
    private MaPtRecDetailService maPtRecDetailService = null;

    public MaPtRecDetailService getMaPtRecDetailService()
    {
        return maPtRecDetailService;
    }

    public void setMaPtRecDetailService(MaPtRecDetailService maPtRecDetailService)
    {
        this.maPtRecDetailService = maPtRecDetailService;
    }

    public MaPtRecDetailDAO getMaPtRecDetailDAO()
    {
        return maPtRecDetailDAO;
    }

    public void setMaPtRecDetailDAO(MaPtRecDetailDAO maPtRecDetailDAO)
    {
        this.maPtRecDetailDAO = maPtRecDetailDAO;
    }

    public MaPtRecListDAO getMaPtRecListDAO() 
    {
        return maPtRecListDAO;
    }

    public void setMaPtRecListDAO(MaPtRecListDAO maPtRecListDAO) 
    {
        this.maPtRecListDAO = maPtRecListDAO;
    }

    public List findList(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {      
        return maPtRecListDAO.findList(maPtRecCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String prRecListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
            prRecListId = deleteRows[i];
            
            // 상태가 입고완료[C]가 아닌 경우만 Delete 한다. 
            String preRecListStatus = maPtRecDetailDAO.findPrRecListStatus(compNo, prRecListId);
            if(!"C".equals(preRecListStatus))
            {
                result = result + maPtRecListDAO.deleteList(compNo, prRecListId);
            }
        }
        
        return result;
    }

    @Override
    public String findTotalCount(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {
        return maPtRecListDAO.findTotalCount(maPtRecCommonDTO, user);
    }

    @Override
    public int insertList(MaPtRecDetailDTO maPtRecDetailDTO, User loginUser) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray();
        if(!"".equals(maPtRecDetailDTO.getMultiDesc())) {
            array = (JSONArray) parser.parse(maPtRecDetailDTO.getMultiDesc());
        } else if(!"".equals(maPtRecDetailDTO.getPoitemId())) {
            array = (JSONArray) parser.parse(maPtRecDetailDTO.getPoitemId());
        }
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = maPtRecListDAO.getNextSequence("SQAPRRECLIST_ID");
            maPtRecDetailDTO.setPrRecListId(seq);
            maPtRecDetailDTO.setPrRecListNo(seq);
            
            if(((String)object.get("PART_ID")) != null) {
                maPtRecDetailDTO.setPartId((String)object.get("PART_ID"));
            } else {
                maPtRecDetailDTO.setPartId((String)object.get("PARTID"));
            }
            if(((String)object.get("PART_NO")) != null) {
                maPtRecDetailDTO.setPartNo((String)object.get("PART_NO"));
            } else {
                maPtRecDetailDTO.setPartNo((String)object.get("PARTNO"));
            }
            if(((String)object.get("DESCRIPTION")) != null) {
                maPtRecDetailDTO.setPartDesc((String)object.get("DESCRIPTION"));
            } else {
                maPtRecDetailDTO.setPartDesc((String) object.get("PARTDESC"));
            }
            if(((String)object.get("PT_SIZE")) != null) {
                maPtRecDetailDTO.setPartSize((String)object.get("PT_SIZE"));
            } else {
                maPtRecDetailDTO.setPartSize((String)object.get("PARTSIZE"));
            }
           
            maPtRecDetailDTO.setIsSerial((String)object.get("IS_SERIAL_PART"));
            maPtRecDetailDTO.setUom((String)object.get("UOM"));
            maPtRecDetailDTO.setWcodeId((String)object.get("WCODEID"));
            maPtRecDetailDTO.setVendorId((String)object.get("VENDORID"));
            maPtRecDetailDTO.setPoitemId((String)object.get("POITEMID"));
            maPtRecDetailDTO.setPartGrade((String)object.get("PARTGRADE"));
            maPtRecDetailDTO.setPolistId((String)object.get("POLISTID"));
            maPtRecDetailDTO.setPtpritemId((String)object.get("PRITEMID"));
                        
            result = result + maPtRecDetailService.insertDetail(maPtRecDetailDTO, loginUser);
        }
        return result;
    }

    @Override
    public int confirmPtRec(String[] selectedPtRecList, User user) throws Exception {
        int result = 0;
        
        MaPtRecDetailDTO maPtRecDetailDTO;
        
        if(selectedPtRecList!=null){
            maPtRecDetailDTO = null;
            for(String id : selectedPtRecList)
            {
                maPtRecDetailDTO = maPtRecDetailDAO.findDetail(user, id);

                //품번생성
                //품번 생성 여부 Y이면 품번생성 프로세스 실행
                if(maPtRecDetailDTO.getIsMakePartNoId().equals("Y")
                        && (maPtRecDetailDTO.getPartNo().equals("") || maPtRecDetailDTO.getPartNo() == null))
                {
                    result +=maPtRecDetailService.makePtMstr(maPtRecDetailDTO, user);
                }
                
                // 입고확정
                // 상태가 입고 확정이 아니라면 입고 확정처리
                if(!"C".equals(maPtRecDetailDTO.getPrRecListStatus()))
                {
                    maPtRecDetailDTO.setPrRecListStatus("C");
                    maPtRecDetailDTO.setPtRecMode("C");
                    
                    result+=maPtRecDetailService.confirmPtRec(maPtRecDetailDTO, user);
                }
                
            }
        }
        
        return result;
    }
    
    public int insertQrCode(String[] selectRows, String fileName, User loginUser) throws Exception{
        
        //일단 지운뒤에 insert하여 이전의  출력 데이터를 삭제한다.
        maPtRecListDAO.deleteQrCode(fileName,loginUser);
        
        int result = 0;
        if(selectRows != null) {
            for(int i=0; i<selectRows.length;i++)
            {
                result = result + maPtRecListDAO.insertQrCode(selectRows[i],fileName,loginUser);
            }
        }
        return result;
    }

    @Override
    public void saveList(List<Map> gridList, User user) throws Exception
    {
        MaPtRecDetailDTO maPtRecDetailDTO = null;
        
        for(Map map : gridList)
        {
            maPtRecDetailDTO = (MaPtRecDetailDTO)CommonUtil.makeDTO(map, MaPtRecDetailDTO.class);
            
//          maPtRecDetailDTO.setCompNo(user.getCompNo());
            
            switch(maPtRecDetailDTO.getNativeeditor())
            {
                case "inserted":
                    break;
                case "updated" : maPtRecDetailService.updateDetail(maPtRecDetailDTO, user);
                    break;
                case "deleted": 
                    break;
            }
            
        }
    }

    @Override
    public String getData(MaPtRecCommonDTO maPtRecCommonDTO, User user)
    {
        maPtRecCommonDTO.setExceltabNo("PTPRRECLIST");
        return maPtRecListDAO.getData(maPtRecCommonDTO, user);
    }

}