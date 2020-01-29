package dream.asset.rpt.lcc.loc.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.rpt.lcc.loc.dao.AssetRptLccLocDetailDAO;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;

/**
 * 고장TOP(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptLccLocDetailDAOTarget"
 * @spring.txbn id="assetRptLccLocDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptLccLocDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptLccLocDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccLocDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String [] monthArr = DateUtil.getArrayBetweenMonth(assetRptLccLocDetailDTO.getStartDate().replaceAll("-", ""), assetRptLccLocDetailDTO.getEndDate().replaceAll("-", ""));
        for(int i=0; i<monthArr.length; i++){
            assetRptLccLocDetailDTO.setStartDate(monthArr[i]);
            assetRptLccLocDetailDTO.setEndDate(monthArr[i]);
            
            if(i != 0) {
                query.append("UNION ALL");
            }
            query.append("SELECT");
            if("0".equals(this.findCount(assetRptLccLocDetailDTO, loginUser))) {
            	query.append("     0                                                                seqNo	      ");
                query.append("    ,SUBSTR('"+monthArr[i]+"',1,4)||'-'||SUBSTR('"+monthArr[i]+"',5,2)month         ");
                query.append("    ,0                                                                bdTimeFreq    ");
                query.append("    ,0                                                                woTimeMin     ");
                query.append("    ,0                                                                maintAmt      ");
                query.append("	  ,0																eqlocId	  	  ");
                query.append("	  ,''																eqlocDesc	  ");
                query.append("FROM DUAL");
            }
            else {
            	query.append("     0                                                                seqNo	      ");
                query.append("    ,SUBSTR('"+monthArr[i]+"',1,4)||'-'||SUBSTR('"+monthArr[i]+"',5,2)month         ");
                query.append("    ,NVL(xx.bdTimeFreq,0)	                                            bdTimeFreq    ");
                query.append("    ,NVL(xx.woTimeMin,0) 	                                   			woTimeMin     ");
                query.append("    ,NVL(xx.maintAmt,0)                                      			maintAmt      ");
                query.append("	  ,NVL(xx.eqLocId,0)												eqlocId	  	  ");
                query.append("	  ,NVL(xx.eqlocDesc,'')												eqlocDesc	  ");
                query.append("FROM                                                                                ");
                query.append("    (                                                                       		  ");
                query.append("SELECT 								                                              ");
                query.append("    ''     		  SEQNO  			                                              ");
                query.append("    ,''     		  ISDELCHECK     	                                              ");
                query.append("    ,x.eqloc_id     eqLocId                                                         ");
                query.append("    ,x.full_desc	  eqLocDesc                                                       ");
                query.append("            ,(SELECT COUNT(1) 		                                              ");
                query.append("             FROM TAEQHISTORY a 		                                              ");
                query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
                query.append("             WHERE eqloc_id IN (													");
                query.append("                                   SELECT eqloc_id								");
                query.append("                                    FROM TAEQLOC 									");
                query.append("                                   START WITH eqloc_id = x.eqloc_id				");
                query.append("                                   CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
                query.append("                                 )												");
                query.append(this.getWhere(assetRptLccLocDetailDTO,loginUser));
                query.append("            ) bdTimeFreq															");
                query.append("            ,(SELECT NVL(SUM(a.work_time),0) 										");
                query.append("             FROM TAEQHISTORY a 													");
                query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
                query.append("             WHERE eqloc_id IN (													");
                query.append("                                             SELECT eqloc_id						");
                query.append("                                             FROM TAEQLOC 						");
                query.append("                                            START WITH eqloc_id = x.eqloc_id		");
                query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id");
                query.append("                                          )			");
                query.append(this.getWhere(assetRptLccLocDetailDTO,loginUser));
                query.append("            ) woTimeMin								");
                query.append("            ,(SELECT NVL(SUM(a.tot_amt),0) 			");
                query.append("             FROM TAEQHISTORY a 						");
                query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
                query.append("             WHERE eqloc_id IN (							");
                query.append("                                      SELECT eqloc_id		");
                query.append("                                       FROM TAEQLOC 		");
                query.append("                                       START WITH eqloc_id = x.eqloc_id				");
                query.append("                                       CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
                query.append("                                  )													");
                query.append(this.getWhere(assetRptLccLocDetailDTO,loginUser));
                query.append("            ) maintAmt            													");
                query.append("FROM TAEQLOC x 		");
                query.append("WHERE 1=1 			");
                query.append("AND x.is_use ='Y'		");
                query.append("AND x.eqloc_id = '"+assetRptLccLocDetailDTO.getEqLocId()+"'					");
                query.append("    ) xx                                                                      ");
                //query.append("GROUP BY month");
            }
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        
        query.append("    AND a.wo_type='BM'        ");
        query.append("    AND a.wkor_date IS NOT NULL       ");
        
        String fromDate = CommonUtil.dateToData(assetRptLccLocDetailDTO.getStartDate()).replace("-", "")+"01";
        String toDate   = CommonUtil.dateToData(assetRptLccLocDetailDTO.getEndDate()).replace("-", "")+"31";
        
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )",
        		assetRptLccLocDetailDTO.getPlantId(), assetRptLccLocDetailDTO.getPlantDesc());
        
        return query.toString();
    }
    
    public String findCount(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                          ");
        query.append("    COUNT(1)                                    ");
        query.append("    FROM TAEQLOC x 							  ");
        query.append("    WHERE 1=1                                   ");
        query.append("	  AND x.is_use ='Y'							  ");
        query.append("AND x.eqloc_id = '"+assetRptLccLocDetailDTO.getEqLocId()+"'					");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    @Override
    public List findMo(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("    a.mo_cd                                               MOCD            ");
        query.append("    ,(SELECT (SELECT key_name                                             ");
        query.append("              FROM talang                                                 ");
        query.append("              WHERE  lang = '"+user.getLangId()+"'                        ");
        query.append("              AND key_type = aa.key_type                                  ");
        query.append("              AND key_no = aa.key_no)                                     ");
        query.append("      FROM TAFAILURE aa                                                   ");
        query.append("      WHERE aa.comp_no = a.comp_no                                        ");
        query.append("      AND aa.failure_id = a.mo_cd                                         ");
        query.append("      AND aa.fail_type = 'MO')                            MODESC          ");
        query.append("    ,COUNT(a.mo_cd)                                       MOCNT           ");
        query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                           ");
        query.append("    ON a.item_no=b.item_no                                                ");
        query.append("    AND a.comp_no=b.comp_no                                               ");
        query.append("    AND b.is_deleted ='N'                                                 ");
        query.append("    AND b.is_last_version ='Y'                                            ");
        query.append("    WHERE 1=1                                                             ");
        query.append("    AND a.mo_cd IS NOT NULL                                               ");
        query.append("    AND b.eqloc_id IN (                                                   ");
        query.append("                       SELECT eqloc_id                                    ");
        query.append("                       FROM TAEQLOC                                       ");
        query.append("                       START WITH eqloc_id = '"+assetRptLccLocDetailDTO.getEqLocId()+"'   ");
        query.append("                       CONNECT BY PRIOR eqloc_id = p_eqloc_id             ");
        query.append("                       )                                                  ");
        query.append(this.getWhere(assetRptLccLocDetailDTO, user));
        query.append("GROUP BY a.comp_no, a.mo_cd                                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    @Override
    public List findCa(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("    a.ca_cd                                               CACD            ");
        query.append("    ,(SELECT (SELECT key_name                                             ");
        query.append("              FROM talang                                                 ");
        query.append("              WHERE  lang = '"+user.getLangId()+"'                        ");
        query.append("              AND key_type = aa.key_type                                  ");
        query.append("              AND key_no = aa.key_no)                                     ");
        query.append("      FROM TAFAILURE aa                                                   ");
        query.append("      WHERE aa.comp_no = a.comp_no                                        ");
        query.append("      AND aa.failure_id = a.ca_cd                                         ");
        query.append("      AND aa.fail_type = 'CA')                            CADESC          ");
        query.append("    ,COUNT(a.ca_cd)                                       CACNT           ");
        query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                           ");
        query.append("    ON a.item_no=b.item_no                                                ");
        query.append("    AND a.comp_no=b.comp_no                                               ");
        query.append("    AND b.is_deleted ='N'                                                 ");
        query.append("    AND b.is_last_version ='Y'                                            ");
        query.append("    WHERE 1=1                                                             ");
        query.append("    AND a.ca_cd IS NOT NULL                                               ");
        query.append("    AND b.eqloc_id IN (                                                   ");
        query.append("                       SELECT eqloc_id                                    ");
        query.append("                       FROM TAEQLOC                                       ");
        query.append("                       START WITH eqloc_id = '"+assetRptLccLocDetailDTO.getEqLocId()+"'   ");
        query.append("                       CONNECT BY PRIOR eqloc_id = p_eqloc_id             ");
        query.append("                       )                                                  ");
        query.append(this.getWhere(assetRptLccLocDetailDTO, user));
        query.append("GROUP BY a.comp_no, a.ca_cd                                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    @Override
    public List findRe(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("    a.re_cd                                               RECD            ");
        query.append("    ,(SELECT (SELECT key_name                                             ");
        query.append("              FROM talang                                                 ");
        query.append("              WHERE  lang = '"+user.getLangId()+"'                        ");
        query.append("              AND key_type = aa.key_type                                  ");
        query.append("              AND key_no = aa.key_no)                                     ");
        query.append("      FROM TAFAILURE aa                                                   ");
        query.append("      WHERE aa.comp_no = a.comp_no                                        ");
        query.append("      AND aa.failure_id = a.re_cd                                         ");
        query.append("      AND aa.fail_type = 'RE')                            REDESC          ");
        query.append("    ,COUNT(a.re_cd)                                       RECNT           ");
        query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                           ");
        query.append("    ON a.item_no=b.item_no                                                ");
        query.append("    AND a.comp_no=b.comp_no                                               ");
        query.append("    AND b.is_deleted ='N'                                                 ");
        query.append("    AND b.is_last_version ='Y'                                            ");
        query.append("    WHERE 1=1                                                             ");
        query.append("    AND a.re_cd IS NOT NULL                                               ");
        query.append("    AND b.eqloc_id IN (                                                   ");
        query.append("                       SELECT eqloc_id                                    ");
        query.append("                       FROM TAEQLOC                                       ");
        query.append("                       START WITH eqloc_id = '"+assetRptLccLocDetailDTO.getEqLocId()+"'   ");
        query.append("                       CONNECT BY PRIOR eqloc_id = p_eqloc_id             ");
        query.append("                       )                                                  ");
        query.append(this.getWhere(assetRptLccLocDetailDTO, user));
        query.append("GROUP BY a.comp_no, a.re_cd                                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
}