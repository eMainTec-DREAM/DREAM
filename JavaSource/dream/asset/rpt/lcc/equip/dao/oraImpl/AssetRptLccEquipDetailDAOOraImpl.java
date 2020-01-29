package dream.asset.rpt.lcc.equip.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.rpt.lcc.equip.dao.AssetRptLccEquipDetailDAO;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipDetailDTO;

/**
 * 고장TOP(설비) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptLccEquipDetailDAOTarget"
 * @spring.txbn id="assetRptLccEquipDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptLccEquipDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptLccEquipDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccEquipDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String [] monthArr = DateUtil.getArrayBetweenMonth(assetRptLccEquipDetailDTO.getStartDate().replaceAll("-", ""), assetRptLccEquipDetailDTO.getEndDate().replaceAll("-", ""));
        for(int i=0; i<monthArr.length; i++){
            assetRptLccEquipDetailDTO.setStartDate(monthArr[i]);
            assetRptLccEquipDetailDTO.setEndDate(monthArr[i]);
            
            if(i != 0) {
                query.append("UNION ALL");
            }
            query.append("SELECT");
            if("0".equals(this.findCount(assetRptLccEquipDetailDTO, loginUser))) {
            	query.append("     ''                                                               seqNo    	  ");
                query.append("    ,SUBSTR('"+monthArr[i]+"',1,4)||'-'||SUBSTR('"+monthArr[i]+"',5,2) month        ");
                query.append("    ,0                                                                bdTimeFreq    ");
                query.append("    ,0                                                                woTimeMin     ");
                query.append("    ,0                                                                maintAmt      ");
                query.append("	  ,0																equipId	  	  ");
                query.append("	  ,''																equipDesc	  ");
                query.append("FROM DUAL");
            }
            else {
            	query.append("     ''                                                               seqNo    	  ");
                query.append("    ,x.month                                                          month         ");
                query.append("    ,COUNT(x.itemNo)                                                  bdTimeFreq    ");
                query.append("    ,NVL(SUM(x.workTime),0)                                           woTimeMin     ");
                query.append("    ,NVL(SUM(x.totAmt),0)                                             maintAmt      ");
                query.append("	  ,NVL(MAX(x.equipId),0)											equipId	  	  ");
                query.append("	  ,NVL(MAX(x.equipDesc),'')											equipDesc	  ");
                query.append("FROM                                                                                ");
                query.append("    (                                                                       ");
                query.append("    SELECT                                                                  ");
                query.append("        a.item_no                                                itemNo     ");
                query.append("        ,SUBSTR(a.wkor_date,1,4)||'-'||SUBSTR(a.wkor_date,5,2)   month      ");
                query.append("        ,a.work_time                                             workTime   ");
                query.append("        ,a.tot_amt                                               totAmt     ");
                query.append("		  ,b.equip_id											   equipId	  ");
                query.append("		  ,b.description										   equipDesc  ");
                query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                             ");
                query.append("    ON a.item_no=b.item_no                                                  ");
                query.append("    AND a.comp_no=b.comp_no                                                 ");
                query.append("    WHERE 1=1                                                               ");
                query.append(this.getWhere(assetRptLccEquipDetailDTO,loginUser));
                query.append("    )x                                                                      ");
                query.append("GROUP BY x.month");
            }
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("b.is_last_version", "Y");
        
        query.append("    AND a.wo_type='BM'        ");
        query.append("    AND a.wkor_date IS NOT NULL       ");
        
        String fromDate = CommonUtil.dateToData(assetRptLccEquipDetailDTO.getStartDate()).replace("-", "") + "01";
        String toDate   = CommonUtil.dateToData(assetRptLccEquipDetailDTO.getEndDate()).replace("-", "") + "31";
        
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);
        
        query.getAndQuery("a.item_no", assetRptLccEquipDetailDTO.getItemNo());
        query.getAndQuery("b.eqloc_id", assetRptLccEquipDetailDTO.getEqLocId());
        query.getAndQuery("b.eqctg_id", assetRptLccEquipDetailDTO.getEqCtgId());
        query.getAndQuery("b.dept_id", assetRptLccEquipDetailDTO.getDeptId());

        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )",
        		assetRptLccEquipDetailDTO.getPlantId(), assetRptLccEquipDetailDTO.getPlantDesc());
      
        return query.toString();
    }
    
    public String findCount(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                          ");
        query.append("    COUNT(1)                                    ");
        query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b ");
        query.append("    ON a.item_no=b.item_no                      ");
        query.append("    AND a.comp_no=b.comp_no                     ");
        query.append("    WHERE 1=1                                   ");
        query.append(this.getWhere(assetRptLccEquipDetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    @Override
    public List findMo(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user)
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
        query.append("    WHERE 1=1                                                             ");
        query.append("    AND a.mo_cd IS NOT NULL                                               ");
        query.append(this.getWhere(assetRptLccEquipDetailDTO, user));
        query.append("GROUP BY a.comp_no, a.mo_cd                                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findCa(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user)
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
        query.append("    WHERE 1=1                                                             ");
        query.append("    AND a.ca_cd IS NOT NULL                                               ");
        query.append(this.getWhere(assetRptLccEquipDetailDTO, user));
        query.append("GROUP BY a.comp_no, a.ca_cd                                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findRe(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user)
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
        query.append("    WHERE 1=1                                                             ");
        query.append("    AND a.re_cd IS NOT NULL                                               ");
        query.append(this.getWhere(assetRptLccEquipDetailDTO, user));
        query.append("GROUP BY a.comp_no, a.re_cd                                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
}