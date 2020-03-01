package net.mamoe.mirai.intellij.builder

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.Disposable
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.roots.ModifiableRootModel
import net.mamoe.mirai.intellij.builder.createDic
import net.mamoe.mirai.intellij.builder.createRoot
import net.mamoe.mirai.intellij.module.MiraiConsolePluginModule.Companion.instance
import net.mamoe.mirai.intellij.ui.MiraiConsoleModuleWizardStep

class MiraiPluginModuleBuilder : ModuleBuilder() {
    @Throws(ConfigurationException::class)
    override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {
        val project = modifiableRootModel.project
        val root = createRoot()?:throw ConfigurationException("创建ROOT文件失败","项目创建失败")
        modifiableRootModel.addContentEntry(root)

        if (moduleJdk != null) {
            modifiableRootModel.sdk = moduleJdk
        }
        this.createDic(root)
    }

    override fun getModuleType(): ModuleType<*> {
        return instance
    }


    override fun getCustomOptionsStep(context: WizardContext, parentDisposable: Disposable): ModuleWizardStep? {
        return MiraiConsoleModuleWizardStep()
    }
}