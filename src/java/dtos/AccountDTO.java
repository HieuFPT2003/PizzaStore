/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Quang Hieu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String accountId;
    private String username;
    private String password;
    private String fullName;
    private int type;   //1: staff - allowed to perform all actions
                        //2: user - allowed to view/create/update the profile, order, and view their orders history
    private Long customerId;
}
