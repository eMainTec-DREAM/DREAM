package dream.doc.file.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaDocLibListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDocLibListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maDocLibCommonDTO.getCompNo();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                                            ");
        query.append("       '' SEQNO,                                                                  ");
        query.append("       '' ISDELCHECK,                                                             ");
        query.append("       x.doc_no DOCNO,                                                            ");
        query.append("       x.description,                                                             ");
        query.append("       dbo.SFACODE_TO_DESC(x.doc_categ , 'DOC_CATEG', 'USR', '"+compNo+"','"+user.getLangId()+"') DOCCATEG,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.secur_grade , 'SECUR_GRADE', 'SYS', '"+compNo+"','"+user.getLangId()+"') SECURGRADE,");
        query.append("       dbo.SFACODE_TO_DESC(x.object_type , 'OBJECT_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"') OBJECTTYPE,");
        query.append("       dbo.SFAIDTODESC(x.dept_id , '', 'DEPT', '"+compNo+"') DEPTID,              ");
        query.append("       dbo.SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"') REGID,                 ");
        query.append("       x.docctg_id DOCCTGID,                                                        ");
        query.append("       (SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id AND a.comp_no = x.comp_no) DOCCTGDESC,");
        query.append("       pubdoc_yn PUBDOCYN,                                                        ");
        query.append("       x.reg_date REGDATE,           												");
        query.append("       x.comp_no COMPNO,                                                          ");
        query.append("       x.doc_id DOCID                                                             ");
        query.append("		, x.remark			remark													");
        query.append("FROM   TADOCUMENT x LEFT OUTER JOIN TAOBJDOC y                                  	");
        query.append("  ON   x.comp_no = y.comp_no                                                      ");
        query.append(" AND   x.doc_id = y.doc_id                                                        ");
        query.append("WHERE  1  = 1                                                                     ");
        query.append(this.getWhere(maDocLibCommonDTO,user));        
        query.getOrderByQuery("x.doc_id","x.doc_no", maDocLibCommonDTO.getOrderBy(), maDocLibCommonDTO.getDirection());
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
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
        query.getCodeLikeQuery("x.reg_id", "dbo.SFAIDTODESC(x.reg_id , '', 'EMP', '"+compNo+"')",  maDocLibCommonDTO.getRegId(),  maDocLibCommonDTO.getRegDesc());
        query.getCodeLikeQuery("x.docctg_id", "(SELECT a.description FROM TADOCCTG a WHERE a.docctg_id = x.docctg_id)",  maDocLibCommonDTO.getDocctgId(),  maDocLibCommonDTO.getDocctgDesc());


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
        
        query.getAndQuery("x.object_type", maDocLibCommonDTO.getObjectType());

        query.getAndQuery("x.doc_no", maDocLibCommonDTO.getObjectNo());
        
        if(!"".equals(maDocLibCommonDTO.getObjectNo()) && maDocLibCommonDTO.getObjectNo() != null) {
            switch(maDocLibCommonDTO.getObjectType())
            {
                case "WI":                  //Imp. Work Progress
                    query.append("    AND y.object_id = (SELECT top 1 gowrkimp_id FROM TAGOWRKIMP WHERE 1=1        ");
                    query.append("                                                               AND gowrkimp_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "WOREQ":            //WoRequest
                    query.append("    AND y.object_id = (SELECT top 1 woreq_id FROM TAWOREQ WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND woreq_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "WOREQRES":       //WoResponse
                    query.append("    AND y.object_id = (SELECT top 1 woreqres_id FROM TAWOREQRES WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND woreqres_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "WRKDAYRPT":      //wrkDayRpt
                    query.append("    AND y.object_id = (SELECT top 1 wrkdayrpt_id FROM TAWRKDAYRPT WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND wrkdayrpt_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "PTTMSTR":          //공기구마스터
                    query.append("    AND y.object_id = (SELECT top 1 part_id FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND part_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "DOCCNTR_CD":     //일반자료실[Common Room]
                    query.append("    AND y.object_id = (SELECT top 1 doccntr_id FROM TADOCCNTR WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND doccntr_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "PR":                    //Purchase Request
                    query.append("    AND y.object_id = (SELECT top 1 ptprlist_id FROM TAPTPRLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptprlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "WORESULT":         //작업결과[Work Order Executing]
                    query.append("    AND y.object_id = (SELECT top 1 wkor_id FROM TAWORKORDER WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND wo_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "DOCCNTR_EC":      //동종기계정보[Same EQ Room]
                    break;
                case "PTREPAIR":           //부품수리[Spare Part Repair]
                    query.append("    AND y.object_id = (SELECT top 1 ptrepairlist_id FROM TAPTREPAIRLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptrepairlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "EQMSTR":             //설비마스터[Equipment]
                    query.append("    AND y.object_id = (SELECT equip_id FROM TAEQUIPMENT WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND item_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND is_last_version = 'Y')     ");
                    break;
                case "PTMSTR":             //자재마스터[Spare Part]
                    query.append("    AND y.object_id = (SELECT top 1 part_id FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND part_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "QNA":                  //질의[QnA]
                    query.append("    AND y.object_id = (SELECT top 1 question_id FROM TAQUESTION WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND question_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "ANS":                   //응답[Answer]
                    query.append("    AND y.object_id = (SELECT top 1 answer_id FROM TAANSWER WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND answer_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "INVTLIST":             //설비투자목록
                    query.append("    AND y.object_id = (SELECT top 1 invtlist_id FROM TAINVTLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND invtlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "INVTPHASE":             //설비투자진행
                    query.append("    AND y.object_id = (SELECT top 1 invtphase_id FROM TAINVTPHASE WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND invtphase_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "INVTPRC":             //설비투자절차
                    query.append("    AND y.object_id = (SELECT top 1 invtprctp_id FROM TAINVTPRCTP WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND invtprctp_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "EMP_CERT":           //보유자격증
                    query.append("    AND y.object_id = (SELECT top 1 empcertlist_id FROM TAEMPCERTLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND empcertlist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "EMP_TRAIN":          //이수교육
                    break;
                case "PTSTK":                //partAdjStk
                    query.append("    AND y.object_id = (SELECT top 1 ptstktakelist_id FROM TAPTSTKTAKELIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptstktakelist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "ASSBASE":             //설비등급 평가기준
                    query.append("    AND y.object_id = (SELECT top 1 assbaselist_id FROM TAASSBASELIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND assbaselist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "FMEA":                 //FMEA[FMEA]
                    query.append("    AND y.object_id = (SELECT top 1 rcmfmea_id FROM TARCMFMEA WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmfmea_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "RCMSYS":             //RCMSYSTEM
                    query.append("    AND y.object_id = (SELECT top 1 rcmlist_id FROM TARCMLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "RCMFUNC":             //
                    query.append("    AND y.object_id = (SELECT top 1 rcmfunc_id FROM TARCMFUNC WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmfunc_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "RCMFFAIL":             //
                    query.append("    AND y.object_id = (SELECT top 1 rcmffail_id FROM TARCMFFAIL WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmffail_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "PMILIST":             //
                    query.append("    AND y.object_id = (SELECT top 1 pminslist_id FROM TAPMINSLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND pminslist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "PTRLRESULT":             //
                    query.append("    AND y.object_id = (SELECT top 1 pmptrlrsltlist_id FROM TAPMPTRLRSLTLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND pmptrlrsltlist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "EMP_EDU":             //
                    query.append("    AND y.object_id = (SELECT top 1 emptrainlist_id FROM TAEMPTRAINLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND emptrainlist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "PTPURREQ":             //
                    query.append("    AND y.object_id = (SELECT top 1 ptpnlist_id FROM TAPTPNLIST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND ptpnlist_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "PMTASK":             //
                    query.append("    AND y.object_id = (SELECT top 1 rcmfmea_id FROM TARCMFMEA WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND rcmfmea_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "STDPOINT":             //
                    query.append("    AND y.object_id = (SELECT stwrk_id FROM TASTWRKLST WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND stwrk_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               AND is_last_version = 'Y')     ");
                    break;
                case "PM_POINT":			// 예방점검 점검항목
                	query.append("    AND y.object_id = (SELECT top 1 wopoint_id FROM TAWOPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND wopoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               )     ");
                	break;
                case "PM_DAY_POINT":		// 일상점검 점검항목
                	query.append("    AND y.object_id = (SELECT top 1 pminsdpoint_id FROM TAPMINSDPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pminsdpoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               )     ");
                	break;
                case "PM_PART_POINT":		// 파트체인지점검 점검항목
                	query.append("    AND y.object_id = (SELECT top 1 pminsdpoint_id FROM TAPMINSDPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pminsdpoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               )     ");
                	break;
                case "PM_ROUTINE_POINT":	// 정기점검 점검항목
                	query.append("    AND y.object_id = (SELECT top 1 pminspoint_id FROM TAPMINSPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pminspoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               )     ");
                	break;
                case "PM_PATROL_POINT":		// 순회점검 점검항목
                	query.append("    AND y.object_id = (SELECT top 1 pmptrlrsltpoint_id FROM TAPMPTRLRSLTPOINT WHERE comp_no = '"+user.getCompNo()+"'        ");
                	query.append("                                                               AND pmptrlrsltpoint_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                	query.append("                                                               )     ");
                	break;
                case "WODAY":      // 일일작업승인
                    query.append("    AND y.object_id = (SELECT top 1 a.wodaylist_id FROM TAWODAYLIST a WHERE a.comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND a.wodaylist_id = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
                    break;
                case "CONTRACT_LIST":		//단가계약설정
                    query.append("    AND y.object_id = (SELECT top 1 contract_id FROM TACONTRACT WHERE comp_no = '"+user.getCompNo()+"'        ");
                    query.append("                                                               AND contract_no = '"+maDocLibCommonDTO.getObjectNo()+"'     ");
                    query.append("                                                               )     ");
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TADOCUMENT							");
    	query.append("WHERE doc_id = '"+id+"'		       		 		");
    	query.append("AND comp_no = '"+user.getCompNo()+"'		        ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

    public int deleteDocData(String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TADOCDATA                 ");
        query.append("WHERE doc_id = '"+id+"'               ");
        query.append("AND comp_no = '"+user.getCompNo()+"'		        ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    public int deleteObjDoc(String id, String objectId, String objectType, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAOBJDOC                  ");
        query.append("WHERE doc_id = '"+id+"'               ");
        query.getAndQuery("object_id", objectId);
        query.getAndQuery("object_type", objectType);
        query.getAndQuery("comp_no", user.getCompNo());
        
        return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public void linkDoc(MaDocLibCommonDTO maDocLibCommonDTO) {
	    QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAOBJDOC                  ");
        query.append("  (objdoc_id,     comp_no,            ");
        query.append("   object_type,   object_id,          ");
        query.append("   doc_id                             ");
        query.append("  )                                   ");
        query.append("  VALUES                              ");
        query.append("  (NEXT VALUE FOR SQAOBJDOC_ID,?,     ");
        query.append("   ?,             ?,                  ");
        query.append("   ?                                  ");
        query.append("  )                                   ");
        
        Object[] objects = new Object[] {
                maDocLibCommonDTO.getCompNo(),
                maDocLibCommonDTO.getObjectType(),
                maDocLibCommonDTO.getObjectId(),
                maDocLibCommonDTO.getAddedDocId()
        };
        
        getJdbcTemplate().update(query.toString(), objects);
	}
    public String findTotalCount(MaDocLibCommonDTO maDocLibCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TADOCUMENT x LEFT OUTER JOIN TAOBJDOC y	");
        query.append("  ON   x.comp_no = y.comp_no                      ");
        query.append(" AND   x.doc_id = y.doc_id                        ");
        query.append("WHERE  1  = 1                                     ");
        query.append(this.getWhere(maDocLibCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

}