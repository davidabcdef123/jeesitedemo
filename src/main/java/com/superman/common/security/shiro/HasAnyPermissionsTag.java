package com.superman.common.security.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/16.</p>
 * Shiro HasAnyPermissions Tag.
 * @author Super.Sun
 * @version 1.0
 */
public class HasAnyPermissionsTag  extends PermissionTag {

    private static final long serialVersionUID = 1L;
    private static final String PERMISSION_NAMES_DELIMETER = ",";

    @Override
    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyPermission = false;

        Subject subject = getSubject();

        if (subject != null) {
            // Iterate through permissions and check to see if the user has one of the permissions
            for (String permission : permissionNames.split(PERMISSION_NAMES_DELIMETER)) {

                if (subject.isPermitted(permission.trim())) {
                    hasAnyPermission = true;
                    break;
                }

            }
        }

        return hasAnyPermission;
    }
}
