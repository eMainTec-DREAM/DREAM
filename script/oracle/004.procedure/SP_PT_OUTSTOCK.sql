/* 
 * 2018-05-25 김정우 수정
 *    수정내용: 출고완료 후 자재 마지막 출고일자 업데이트
 * */
CREATE OR REPLACE procedure SP_PT_OUTSTOCK(
      v_comp_no                         in varchar2 
     ,v_ptisshist_id                      in number
)is
    v_count                  number(4);  
    
    cursor c_iss_list is
        select 
            comp_no
            ,ptisshist_id
            ,ptiss_mode
            ,ptiss_type
            ,dept_id
            ,wcode_id
            ,iss_date
            ,part_id
            ,DECODE(part_grade,'A','A','B','B','B')  as part_grade
            ,nvl(iss_qty,0) as iss_qty
            ,nvl(unit_price,0) as unit_price
        from TAPTISSHIST
        where 1=1
            and comp_no = v_comp_no
            and ptisshist_id = v_ptisshist_id
        ;
        
    
begin
    
       
       for c1 in c_iss_list loop
           
           if c1.ptiss_mode = 'C' then  -- 출고
               select count(*)
                into v_count
                from taptstock
                where 1=1
                    and comp_no = c1.comp_no
                    and wcode_id =c1.wcode_id
                    and part_id = c1.part_id
                    and part_grade = c1.part_grade
                ;
                   
                if v_count > 0 then
                    update taptstock set
                         stock_qty = nvl(stock_qty,0) - c1.iss_qty
                    where 1=1
                         and comp_no = c1.comp_no
                         and wcode_id =c1.wcode_id
                         and part_id = c1.part_id
                         and part_grade = c1.part_grade
                    ;
                else
                    insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                    values(c1.comp_no, c1.wcode_id, c1.part_id, c1.part_grade, c1.iss_qty * -1)
                    ;
                end if;
                
                update taparts set
                    last_iss_date = to_char(sysdate,'yyyymmdd')
                where comp_no = c1.comp_no
                    and part_id = c1.part_id
                ;
                   
           
           elsif c1.ptiss_mode = 'R' then  -- 취소
               select count(*)
                into v_count
                from taptstock
                where 1=1
                    and comp_no = c1.comp_no
                    and wcode_id =c1.wcode_id
                    and part_id = c1.part_id
                    and part_grade = c1.part_grade
               ;
                   
               if v_count > 0 then
                   update taptstock set
                        stock_qty = nvl(stock_qty,0) + c1.iss_qty
                   where 1=1
                        and comp_no = c1.comp_no
                        and wcode_id =c1.wcode_id
                        and part_id = c1.part_id
                        and part_grade = c1.part_grade
                   ;
               else
                   insert into taptstock(comp_no, wcode_id, part_id, part_grade, stock_qty)
                   values(c1.comp_no, c1.wcode_id, c1.part_id, c1.part_grade, c1.iss_qty )
                   ;
               end if;
           
           end if;
           
       end loop;
       
       

    
end;
/
