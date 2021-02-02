package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.TranslationTextComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.utils.TextUtils;

import javax.annotation.Nonnull;

public class ConfigScreen extends Screen {

    private TextFieldWidget widget;
    private final Screen previousScreen;
    private final TranslationTextComponent GUI_SECTION = new TranslationTextComponent("config.section.gui");
    private final TranslationTextComponent PLAYER_DEATHS_DESCRIPTION = new TranslationTextComponent("config.number_player_deaths.description");
    private final TranslationTextComponent PLAYER_DEATHS_TITLE = new TranslationTextComponent("config.number_player_deaths.title");

    public ConfigScreen(Screen previousScreen) {
        super(new TranslationTextComponent(String.format("screen.%s.config", Utilcraft.MOD_ID)));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        widget = new TextFieldWidget(font, this.width / 2 - 100, 116, 200, 20, new TranslationTextComponent("config.number_player_deaths"));
        widget.setMaxStringLength(2);
        widget.setFocused2(true);
        widget.setText(String.valueOf(Config.DEATHS_OVERLAY_PLAYERS.get()));
        children.add(widget);
        setFocusedDefault(widget);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        TextUtils.drawCenterText(matrixStack, font, title.getString(),  width, 2, 0xFFFFFF);
        drawString(matrixStack, font, GUI_SECTION, width / 2 - font.getStringWidth(GUI_SECTION.getString())/2, 60, 0xCCF7B5);
        drawString(matrixStack, font, PLAYER_DEATHS_TITLE, width / 2 - font.getStringWidth(PLAYER_DEATHS_TITLE.getString())/2, 90, 0xCCF7B5);
        drawString(matrixStack, font, PLAYER_DEATHS_DESCRIPTION, width / 2 - font.getStringWidth(PLAYER_DEATHS_DESCRIPTION.getString())/2, 145, 0xCCF7B5);
        widget.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        checkInput();
    }

    private void checkInput() {
        try {
            int config = Integer.parseInt(widget.getText());
            if (config >= 0 && config <= Config.DEATHS_OVERLAY_PLAYERS_MAX) {
                Config.DEATHS_OVERLAY_PLAYERS.set(config);
                Config.DEATHS_OVERLAY_PLAYERS.save();
            }
        } catch (NumberFormatException ignored) {}
    }

    @Override
    public void tick() {
        widget.tick();
    }

    @Override
    public void closeScreen() {
        minecraft.displayGuiScreen(this.previousScreen);
    }
}
