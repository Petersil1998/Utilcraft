package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.screen.widget.ColorChooser;
import net.petersil98.utilcraft.utils.TextUtils;

import javax.annotation.Nonnull;

public class ConfigScreen extends Screen {

    private TextFieldWidget deaths;
    private ColorChooser colorChooser;
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
        this.deaths = new TextFieldWidget(this.font, this.width / 2 - 100, 116, 200, 20, new TranslationTextComponent("config.number_player_deaths"));
        this.deaths.setMaxStringLength(2);
        this.deaths.setFocused2(true);
        this.deaths.setText(String.valueOf(Config.DEATHS_OVERLAY_PLAYERS.get()));
        this.children.add(this.deaths);
        this.setFocusedDefault(this.deaths);

        this.colorChooser = new ColorChooser(this.font, this.width/2 - 50,200, 100, 20,new StringTextComponent("Death Point Ray Color"));
        this.children.add(this.colorChooser);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        TextUtils.drawCenterText(matrixStack, this.font, this.title.getString(),  this.width, 2, 0xFFFFFF);
        drawString(matrixStack, this.font, this.GUI_SECTION, this.width / 2 - this.font.getStringWidth(this.GUI_SECTION.getString())/2, 60, 0xCCF7B5);
        drawString(matrixStack, this.font, this.PLAYER_DEATHS_TITLE, this.width / 2 - this.font.getStringWidth(this.PLAYER_DEATHS_TITLE.getString())/2, 90, 0xCCF7B5);
        drawString(matrixStack, this.font, this.PLAYER_DEATHS_DESCRIPTION, this.width / 2 - this.font.getStringWidth(this.PLAYER_DEATHS_DESCRIPTION.getString())/2, 145, 0xCCF7B5);
        this.deaths.render(matrixStack, mouseX, mouseY, partialTicks);
        this.colorChooser.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        this.checkInput();
    }

    private void checkInput() {
        try {
            int config = Integer.parseInt(this.deaths.getText());
            if (config >= 0 && config <= Config.DEATHS_OVERLAY_PLAYERS_MAX) {
                Config.DEATHS_OVERLAY_PLAYERS.set(config);
                Config.DEATHS_OVERLAY_PLAYERS.save();
            }
        } catch (NumberFormatException ignored) {}
        Config.DEATH_RAY_COLOR.set(this.colorChooser.getColor());
        Config.DEATH_RAY_COLOR.save();
    }

    @Override
    public void tick() {
        this.deaths.tick();
    }

    @Override
    public void closeScreen() {
        this.minecraft.displayGuiScreen(this.previousScreen);
    }
}
