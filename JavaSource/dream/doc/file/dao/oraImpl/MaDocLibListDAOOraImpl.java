package dream.doc.file.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.doc.file.dao.MaDocLibListDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 첨부파일 - 목록 dao
 * @author  jung7126
 * @version $Id: MaDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maDocLibListDAOTarget"
 * @spring.txbn id="maDocLibListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDocLibListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaDocLibListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibCommonDTO
     * @return List
     */
    public List findList(MaDocLibCommonDTO maDocLibCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maDocLibCommonDTO.getCompNo();
        
        query.append("SELECT                                                                            ");
        query.append("       '' seqNo,                                                                  ");
        query.append("       '' isDelCheck,                                                             ");
        query.append("       x.doc_no docNo,                                                            ");
        query.append("       x.description,                                                             ");
        query.append("       SFACODE_TO_DESC(x.doc_categ , 'DOC_CATEG', 'USR', '"+compNo+"','"+user.getLangId()+"') docCateg,      ");
        query.append("       SFACODE_TO_DESC(x.secur_grade , 'SECUR_GRADE', 'SYS', '"+compNo+"','"+user.getLangId()+"') securGrade,");
        query.append("       SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') objectType,");
        query.append("       SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') deptId,                  ");
        query.append("       SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') regId,                     ");
        query.append("       docctg_id docctgId,														");
        query.append("       (SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id AND a.comp_no = x.comp_no) docctgDesc,");
        query.append("       pubdoc_yn pubdocYn,														");
        query.append("       x.reg_date                                              regDate,           ");
        query.append("       x.comp_no compNo,                                                          ");
        query.append("       x.doc_id docId                                                             ");
        query.append("		, x.remark			remark													");
        query.append("FROM TADOCUMENT x LEFT OUTER JOIN TAOBJDOC y                           			");
        query.append("    ON x.comp_no = y.comp_no                                                  	");
        query.append("    AND x.doc_id = y.doc_id                                                   	");
        query.append("WHERE  1  = 1                                                                     ");
        query.append(this.getWhere(maDocLibCommonDTO,user));
        query.getOrderByQuery("x.doc_id", maDocLibCommonDTO.getOrderBy(), maDocLibCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maDocLibCommonDTO.getIsLoadMaxCount(), maDocLibCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: MaDocLibListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaDocLibCommonDTO maDocLibCommonDTO, User user)
    {        
        String compNo = maDocLibCommonDTO.getCompNo();
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maDocLibCommonDTO.getCompNo());
        if (!"".equals(maDocLibCommonDTO.getDocId()))
        {
            query.getAndQuery("x.doc_id", maDocLibCommonDTO.getDocId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maDocLibCommonDTO.getDocDesc());
        //비고
        query.getLikeQuery("x.remark", maDocLibCommonDTO.getRemark());
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		maDocLibCommonDTO.getPlantId(), maDocLibCommonDTO.getPlantDesc());
        query.getUsrCdQuery("x.doc_categ", maDocLibCommonDTO.getDocCateg(), maDocLibCommonDTO.getDocCategDesc(), "DOC_CATEG", compNo,user.getLangId());
        query.getDeptLevelQuery("x.dept_id", maDocLibCommonDTO.getRegDeptId(), maDocLibCommonDTO.getRegDeptDesc(), compNo);
        query.getCodeLikeQuery("x.reg_id", "SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"')",  maDocLibCommonDTO.getRegId(),  maDocLibCommonDTO.getRegDesc());
        query.getCodeLikeQuery("docctg_id", "(SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id)",  maDocLibCommonDTO.getDocctgId(),  maDocLibCommonDTO.getDocctgDesc());


        if(maDocLibCommonDTO.getObjectId() != "")
        {
        	query.getAndQuery("y.object_id",maDocLibCommonDTO.getObjectId());
        	query.getAndQuery("y.object_type",maDocLibCommonDTO.getObjectType());
        }
        
        if(maDocLibCommonDTO.getFilterEquipDesc() != "" || maDocLibCommonDTO.getFilterEquipId() != "")
        {
            query.append(" AND y.object_id  IN (        ");
            query.append("                      SELECT  ");
            query.append("                                x.invtlist_id ");
            query.append("                      FROM TAINVTLIST x INNER JOIN TAINVTEQUIP y ON x.invtlist_id = y.invtlist_id ");
            query.append("                      inner join taequipment z on y.equip_id = z.equip_id     ");
            query.append("                      WHERE 1 = 1 ");
            query.getCodeLikeQuery("z.equip_id", "z.description", maDocLibCommonDTO.getFilterEquipId(), maDocLibCommonDTO.getFilterEquipDesc());
            query.append("                      UNION ALL   ");
            query.append("                      SELECT  ");
            query.append("                                x.equip_id    ");
            query.append("                      FROM TAEQUIPMENT x  ");
            query.append("                      WHERE 1 = 1 ");
            query.getCodeLikeQuery("x.equip_id", "x.description", maDocLibCommonDTO.getFilterEquipId(), maDocLibCommonDTO.getFilterEquipDesc());
            query.append("                     )   ");
            query.append("  AND x.object_type IN ('INVTLIST','EQMSTR')  ");

        }
        
//        query.getAndQuery("x.object_type", maDocLibCommonDTO.getObjectType());
        
        if(!"".equals(maDocLibCommonDTO.getObjectNo()) && maDocLibCommonDTO.getObjectNo() != null) {
            switch(maDocLibCommonDTO.getObjectType())
            {
                case "WI":                  //Imp. Work Progress
                    query.append("    AND y.object_id = (SELECT gowrkimp_id FROM TAGOWRKIMP WHERE 1=1        ");
                    query.append("                                                               AND gowrkimp_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "WOREQ":            //WoRequest
                    query.append("    AND y.object_id = (SELECT woreq_id FROM TAWOREQ WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND woreq_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "WOREQRES":       //WoResponse
                    query.append("    AND y.object_id = (SELECT woreqres_id FROM TAWOREQRES WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND woreqres_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "WRKDAYRPT":      //wrkDayRpt
                    query.append("    AND y.object_id = (SELECT wrkdayrpt_id FROM TAWRKDAYRPT WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND wrkdayrpt_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "PTTMSTR":          //공기구마스터
                    query.append("    AND y.object_id = (SELECT part_id FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND part_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "DOCCNTR_CD":     //일반자료실[Common Room]
                    query.append("    AND y.object_id = (SELECT doccntr_id FROM TADOCCNTR WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND doccntr_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "PR":                    //Purchase Request
                    query.append("    AND y.object_id = (SELECT ptprlist_id FROM TAPTPRLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptprlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "WORESULT":         //작업결과[Work Order Executing]
                    query.append("    AND y.object_id = (SELECT wkor_id FROM TAWORKORDER WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND wo_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "DOCCNTR_EC":      //동종기계정보[Same EQ Room]
                    break;
                case "PTREPAIR":           //부품수리[Spare Part Repair]
                    query.append("    AND y.object_id = (SELECT ptrepairlist_id FROM TAPTREPAIRLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptrepairlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "EQMSTR":             //설비마스터[Equipment]
                    query.append("    AND y.object_id = (SELECT equip_id FROM TAEQUIPMENT WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND item_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND is_last_version = 'Y')     ");
                    break;
                case "PTMSTR":             //자재마스터[Spare Part]
                    query.append("    AND y.object_id = (SELECT part_id FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND part_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "QNA":                  //질의[QnA]
                    query.append("    AND y.object_id = (SELECT question_id FROM TAQUESTION WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND question_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "ANS":                   //응답[Answer]
                    query.append("    AND y.object_id = (SELECT answer_id FROM TAANSWER WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND answer_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "INVTLIST":             //설비투자목록
                    query.append("    AND y.object_id = (SELECT invtlist_id FROM TAINVTLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND invtlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "INVTPHASE":             //설비투자진행
                    query.append("    AND y.object_id = (SELECT invtphase_id FROM TAINVTPHASE WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND invtphase_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "INVTPRC":             //설비투자절차
                    query.append("    AND y.object_id = (SELECT invtprctp_id FROM TAINVTPRCTP WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND invtprctp_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "EMP_CERT":           //보유자격증
                    query.append("    AND y.object_id = (SELECT empcertlist_id FROM TAEMPCERTLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND empcertlist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "EMP_TRAIN":          //이수교육
                    break;
                case "PTSTK":                //partAdjStk
                    query.append("    AND y.object_id = (SELECT ptstktakelist_id FROM TAPTSTKTAKELIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptstktakelist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "ASSBASE":             //설비등급 평가기준
                    query.append("    AND y.object_id = (SELECT assbaselist_id FROM TAASSBASELIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND assbaselist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "FMEA":                 //FMEA[FMEA]
                    query.append("    AND y.object_id = (SELECT rcmfmea_id FROM TARCMFMEA WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmfmea_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "RCMSYS":             //RCMSYSTEM
                    query.append("    AND y.object_id = (SELECT rcmlist_id FROM TARCMLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "RCMFUNC":             //
                    query.append("    AND y.object_id = (SELECT rcmfunc_id FROM TARCMFUNC WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmfunc_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "RCMFFAIL":             //
                    query.append("    AND y.object_id = (SELECT rcmffail_id FROM TARCMFFAIL WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmffail_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "PMILIST":             //
                    query.append("    AND y.object_id = (SELECT pminslist_id FROM TAPMINSLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND pminslist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "PTRLRESULT":             //
                    query.append("    AND y.object_id = (SELECT pmptrlrsltlist_id FROM TAPMPTRLRSLTLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND pmptrlrsltlist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "EMP_EDU":             //
                    query.append("    AND y.object_id = (SELECT emptrainlist_id FROM TAEMPTRAINLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND emptrainlist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "PTPURREQ":             //
                    query.append("    AND y.object_id = (SELECT ptpnlist_id FROM TAPTPNLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptpnlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "PMTASK":             //
                    query.append("    AND y.object_id = (SELECT rcmfmea_id FROM TARCMFMEA WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmfmea_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "STDPOINT":             //
                    query.append("    AND y.object_id = (SELECT stwrk_id FROM TASTWRKLST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND stwrk_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND is_last_version = 'Y')     ");
                    break;
                case "PM_POINT":			// 예방점검 점검항목
                	query.append("    AND y.object_id = (SELECT wopoint_id FROM TAWOPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND wopoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               AND ROWNUM = 1)     ");
                	break;
                case "PM_DAY_POINT":		// 일상점검 점검항목
                	query.append("    AND y.object_id = (SELECT pminsdpoint_id FROM TAPMINSDPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pminsdpoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               AND ROWNUM = 1)     ");
                	break;
                case "PM_PART_POINT":		// 파트체인지점검 점검항목
                	query.append("    AND y.object_id = (SELECT pminsdpoint_id FROM TAPMINSDPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pminsdpoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               AND ROWNUM = 1)     ");
                	break;
                case "PM_ROUTINE_POINT":	// 정기점검 점검항목
                	query.append("    AND y.object_id = (SELECT pminspoint_id FROM TAPMINSPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pminspoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               AND ROWNUM = 1)     ");
                	break;
                case "PM_PATROL_POINT":		// 순회점검 점검항목
                	query.append("    AND y.object_id = (SELECT pmptrlrsltpoint_id FROM TAPMPTRLRSLTPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pmptrlrsltpoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               AND ROWNUM = 1)     ");
                	break;
                case "WODAY":      // 일일작업승인
                    query.append("    AND y.object_id = (SELECT a.wodaylist_id FROM TAWODAYLIST a WHERE a.comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND a.wodaylist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND ROWNUM = 1)     ");
                    break;
                case "CONTRACT_LIST":		//단가계약설정
                    query.append("    AND y.object_id = (SELECT contract_id FROM TACONTRACT WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND contract_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND rownum = 1)     ");
                    break;
                default:
                    break;
            }
            
            
            
        }
        
        return query.toString();
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaDocLibListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteDoc(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TADOCUMENT				");
    	query.append("WHERE doc_id = '"+id+"'		        ");
    	query.append("AND comp_no = '"+user.getCompNo()+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

    public int deleteDocData(String id, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TADOCDATA                 ");
        query.append("WHERE doc_id = '"+id+"'               ");
    	query.append("AND comp_no = '"+user.getCompNo()+"'	");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    public int deleteObjDoc(String id, String objectId, String objectType, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE FROM TAOBJDOC                  ");
        query.append("WHERE doc_id = '"+id+"'               ");
        query.getAndQuery("object_id", objectId);
        query.getAndQuery("object_type", objectType);
        query.getAndQuery("comp_no", user.getCompNo());
        
        
        return this.getJdbcTemplate().update(query.toString());
    }

	public void linkDoc(MaDocLibCommonDTO maDocLibCommonDTO) 
	{
		QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAOBJDOC		        	");
    	query.append("	(objdoc_id,		comp_no,			");
    	query.append("	 object_type,   object_id,			");
    	query.append("   doc_id                             ");
    	query.append("  )                                   ");
    	query.append("	VALUES					            ");
    	query.append("	(sqaobjdoc_id.nextval,?,			");
    	query.append("	 ?,				?,					");
    	query.append("   ?                                  ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maDocLibCommonDTO.getCompNo(),
    			maDocLibCommonDTO.getObjectType(),
    			maDocLibCommonDTO.getObjectId(),
    			maDocLibCommonDTO.getAddedDocId()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
		
	}
	
	public String findTotalCount(MaDocLibCommonDTO maDocLibCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM TADOCUMENT x LEFT OUTER JOIN TAOBJDOC y      ");
        query.append("    ON x.comp_no = y.comp_no                      ");
        query.append("    AND x.doc_id = y.doc_id                       ");
        query.append("WHERE  1  = 1                                     ");
        query.append(this.getWhere(maDocLibCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }    
}