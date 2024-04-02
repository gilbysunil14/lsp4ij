/*******************************************************************************
 * Copyright (c) 2024 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.microshed.lsp4ij.usages;

import com.intellij.psi.PsiElement;
import com.intellij.usages.UsageTarget;
import com.intellij.usages.impl.rules.UsageType;
import com.intellij.usages.impl.rules.UsageTypeProviderEx;
import org.microshed.lsp4ij.LanguageServerBundle;
import org.jetbrains.annotations.NotNull;

/**
 * LSP usage type provider to show results in the Usages view, categorized by:
 *
 * <ul>
 *     <li>Declarations</li>
 *     <li>Definitions</li>
 *     <li>Type Definitions</li>
 *     <li>References</li>
 *     <li>Implementations</li>
 * </ul>>
 */
public class LSPUsageTypeProvider implements UsageTypeProviderEx {

    private static final UsageType DECLARATIONS_USAGE_TYPE = new UsageType(LanguageServerBundle.messagePointer("usage.type.declarations"));
    private static final UsageType DEFINITIONS_USAGE_TYPE = new UsageType(LanguageServerBundle.messagePointer("usage.type.definitions"));
    private static final UsageType TYPE_DEFINITIONS_USAGE_TYPE = new UsageType(LanguageServerBundle.messagePointer("usage.type.typeDefinitions"));

    private static final UsageType REFERENCES_USAGE_TYPE = new UsageType(LanguageServerBundle.messagePointer("usage.type.references"));

    private static final UsageType IMPLEMENTATIONS_USAGE_TYPE = new UsageType(LanguageServerBundle.messagePointer("usage.type.implementations"));

    @Override
    public UsageType getUsageType(final @NotNull PsiElement element) {
        return getUsageType(element, UsageTarget.EMPTY_ARRAY);
    }

    @Override
    public UsageType getUsageType(PsiElement element, UsageTarget @NotNull [] targets) {
        if (element instanceof LSPUsagePsiElement usageElement) {
            switch (usageElement.getKind()) {
                case declarations:
                    return DECLARATIONS_USAGE_TYPE;
                case definitions:
                    return DEFINITIONS_USAGE_TYPE;
                case typeDefinitions:
                    return TYPE_DEFINITIONS_USAGE_TYPE;
                case references:
                    return REFERENCES_USAGE_TYPE;
                case implementations:
                    return IMPLEMENTATIONS_USAGE_TYPE;
            }
        }
        return null;
    }
}
